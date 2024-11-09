package beans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import services.ProductService;
import models.Category;
import models.Product;

@ManagedBean(name = "adminStatistics")
@SessionScoped
public class AdminStatistics {

    private PieChartModel salesChart;
    private BarChartModel categoryChart;
    private ProductService productService = new ProductService(); // Assuming ProductService handles DB operations

    @PostConstruct
    public void init() {
        createSalesChart();
        createCategoryChart();
    }

    public PieChartModel getSalesChart() {
        return salesChart;
    }
    
    public BarChartModel getCategoryChart() {
        return categoryChart;
    }

    private void createSalesChart() {
        salesChart = new PieChartModel();
        
        List<Product> products = productService.list();
        for (Product product : products) {
            salesChart.set(product.getName(), product.getQuantity().doubleValue()); // Assuming quantity represents sales
        }

        // Customize the Pie Chart (optional)
        salesChart.setTitle("Product Sales Distribution");
        salesChart.setLegendPosition("w");
        salesChart.setShowDataLabels(true);
    }
    
    private void createCategoryChart() {
        categoryChart = new BarChartModel();
        
        ChartSeries categories = new ChartSeries();
        categories.setLabel("Categories");

        // Example: Group by categories and count products per category
        Map<Category, Long> categoryCounts = productService.countProductsByCategory();
        for (Map.Entry<Category, Long> entry : categoryCounts.entrySet()) {
            categories.set(entry.getKey(), entry.getValue());
        }

        categoryChart.addSeries(categories);
        categoryChart.setTitle("Products per Category");
//        categoryChart.setLegendPosition("ne");
        categoryChart.setAnimate(true);
    }
}
