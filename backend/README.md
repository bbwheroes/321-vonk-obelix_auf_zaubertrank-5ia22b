# Obelix & Co. Webshop 🪨
A webshop that was a monolith and is now split into microservices, including tracing and visualization.

# Structure
The webshop consists of three microservices:
- **Webshop**
- **Quarry**
- **Basket**

# Prerequisites
You need Grafana, Prometheus, and a Zipkin instance running in Docker.

# Setup

1. Clone this repository and build it using IntelliJ.

2. Start the Microservices.

3. Make sure that Zipkin in running.

4. Configure Prometheus:
   ```yaml
   metrics_path: "/actuator/prometheus"
   static_configs:
     - targets: ["localhost:8080", "localhost:8081", "localhost:8082"]
    ```
    Then Start Prometheus!

5. Configure Grafana:

    - Go to: http://localhost:3000/
    - Connections → Data sources: Add a new source (Prometheus) with the default URL: http://localhost:9090
    - Dashboards: Create a new dashboard, then add a visualization and select the Prometheus as data source.
    - Change the visualization type to Gauge.
    - For Metric, select "menhir_count", then save the dashboard.

