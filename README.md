# ejb_exporter
Export ejb metrics from oracle table to prometheus

## demo environment
To create the demo environment, run the following command from the docker folder:
docker-compose up -d

### Apache HTTP Server

The Apache HTTP server exposes a web page, stored in the app folder of the project folder, on port 8080. It is accessed using the hostname localhost from the workstation and apache from the other created Docker containers.

The bitnami.conf file in the project folder is a modified version of a file in the bitnami/apache image; in particular it enables the Apache HTTP Server to serve its status, including key metrics, on the endpoint /server-status.

This endpoint supports the auto parameter, e.g., http://localhost:8080/server-status?auto, which is a machine-friendly version of the same status information.

### Apache Exporter for Prometheus

As the Apache HTTP server’s status endpoint is not a Prometheus compatible HTTP endpoint, the Apache Exporter for Prometheus server scrapes it and exposes a Prometheus compatible HTTP endpoint on port 9117 and path /metrics. It is accessed using the hostname localhost from the workstation and apache-exporter from other created Docker containers.

### Prometheus

Finally, there is the Prometheus server that is configured, via the prometheus.yml file in the project folder, to collect metrics from the Apache Exporter for Prometheus server.

It exposes a user interface on port 9090. As with the other services, it is accessed using the hostname localhost.
