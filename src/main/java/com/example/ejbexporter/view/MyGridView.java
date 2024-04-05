package com.example.ejbexporter.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

@Route("grid")
public class MyGridView extends VerticalLayout {

    private final Grid<Map<String, Object>> grid;
    private JdbcTemplate jdbcTemplate;

    public MyGridView(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
         grid = new Grid<>();

         String sql = "SELECT b.titel, b.check_intervall, b.error_schwellwert, a.result FROM ekp.fvm_monitor_result a, ekp.fvm_monitoring b WHERE a.id = b.id AND a.is_active = 1";
        List<Map<String, Object>> rowsData = jdbcTemplate.queryForList(sql);

        if (rowsData != null) {

            grid.setItems(rowsData);

            // Add columns dynamically based on the keys in the first row (assuming all rows have the same keys)
            Set<String> columns = rowsData.isEmpty() ?
                    Collections.emptySet() : rowsData.get(0).keySet();

            for (String column : columns) {
                grid.addColumn(row -> row.get(column)).setHeader(column).setAutoWidth(true).setResizable(true);
            }
          }


        // Add the grid to the layout
        add(grid);
    }

    // Item class representing the structure of items displayed in the grid
    public static class Item {
        private int id;
        private String name;
        private String description;

        public Item(int id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }

        // Getters and setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
