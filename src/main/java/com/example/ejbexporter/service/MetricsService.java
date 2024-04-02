package com.example.ejbexporter.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MetricsService {

    private final JdbcTemplate jdbcTemplate;

    public MetricsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public String fetchMetrics() {
        String sqlQuery = "SELECT b.titel, a.result FROM ekp.fvm_monitor_result a, ekp.fvm_monitoring b WHERE a.id = b.id AND a.is_active = 1";

        List<Map<String, Object>> queryResult = jdbcTemplate.queryForList(sqlQuery);

        StringBuilder metricsBuilder = new StringBuilder();

        // Append HELP and TYPE lines for each metric if needed
        // metricsBuilder.append("# HELP <metric_name> <help_text>\n");
        // metricsBuilder.append("# TYPE <metric_name> <metric_type>\n");

        for (Map<String, Object> row : queryResult) {
            String title = (String) row.get("titel");
            BigDecimal result = (BigDecimal) row.get("result");

            // Format the metrics into Prometheus format
            String metricLine = String.format("ekp_metric{title=\"%s\"} %s\n", escapeString(title), result);

            metricsBuilder.append(metricLine);
        }

        // Ensure the last line ends with a line feed character
        if (metricsBuilder.length() > 0 && metricsBuilder.charAt(metricsBuilder.length() - 1) != '\n') {
            metricsBuilder.append('\n');
        }

        // Append # EOF at the end
        metricsBuilder.append("# EOF\n");

        return metricsBuilder.toString();
    }

    // Helper method to escape special characters in label values
    private String escapeString(String str) {
        // Escape backslashes, double-quotes, and line feeds
        return str
                .replaceAll("\\\\", "\\\\\\\\")
                .replaceAll("\"", "\\\\\"")
                .replaceAll("\n", "\\\\n");
    }

}
