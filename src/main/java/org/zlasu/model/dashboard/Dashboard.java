package org.zlasu.model.dashboard;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard {

    private int totalOrders;
    private Double totalIncome;
    private Double totalCosts;

    public Dashboard() {
        DashboardDao dashboardDao = new DashboardDao();
        this.totalOrders = dashboardDao.getOrdersCount();
        this.totalIncome = dashboardDao.getTotalIncome();
        this.totalCosts = dashboardDao.getTotalCosts();
    }
}
