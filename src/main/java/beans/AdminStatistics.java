package beans;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.chart.ChartSeries;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.HashMap;
import java.util.Map;

@ManagedBean(name = "adminStatistics")
@SessionScoped
public class AdminStatistics {

    private PieChartModel salesChart;
    private BarChartModel barChart;

    public AdminStatistics() {
        // Initializing charts
        createSalesChart();
        createBarChart();
    }

    // Getters for the charts
    public PieChartModel getSalesChart() {
        return salesChart;
    }

    public BarChartModel getBarChart() {
        return barChart;
    }

    // Create Pie Chart for Sales Data
    private void createSalesChart() {
        salesChart = new PieChartModel();

        // Example: You can replace these values with actual data from your database
        salesChart.set("Product A", 100); // Example value
        salesChart.set("Product B", 200); // Example value
        salesChart.set("Product C", 150); // Example value

        // Customize the Pie Chart (optional)
        salesChart.setTitle("Product Sales Distribution");
        salesChart.setLegendPosition("w");
        salesChart.setShowDataLabels(true);
    }

    // Create Bar Chart for Sales Comparison
    private void createBarChart() {
        barChart = new BarChartModel();

        // Create a series for the bar chart (Example: Sales for different months)
        ChartSeries sales = new ChartSeries();
        sales.setLabel("Sales");
        sales.set("January", 120);  // Example value
        sales.set("February", 150); // Example value
        sales.set("March", 200);    // Example value
        sales.set("April", 180);    // Example value

        // Add the series to the Bar Chart
        barChart.addSeries(sales);

        // Customize the Bar Chart (optional)
        barChart.setTitle("Sales Over Time");
        barChart.setLegendPosition("ne");
        barChart.setShowPointLabels(true);
    }
}
