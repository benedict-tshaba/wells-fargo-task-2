package com.wellsfargo.counselor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public class Security {

    @Id
    @GeneratedValue
    private Long securityId; // Changed to Long

    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String purchaseDate;

    @Column(nullable = false)
    private double purchasePrice;

    @Column(nullable = false)
    private int quantity;

    // Protected constructor for JPA
    protected Security() {}

    public Security(Portfolio portfolio, String name, String category, String purchaseDate, double purchasePrice, int quantity) {
        this.portfolio = portfolio;
        this.name = name;
        this.category = category;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
    }

    // Getters
    public Long getSecurityId() {
        return securityId;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Security{" +
                "securityId=" + securityId +
                ", portfolio=" + portfolio.getPortfolioId() +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Security)) return false;
        Security security = (Security) o;
        return Double.compare(security.purchasePrice, purchasePrice) == 0 &&
               quantity == security.quantity &&
               Objects.equals(portfolio, security.portfolio) &&
               Objects.equals(name, security.name) &&
               Objects.equals(category, security.category) &&
               Objects.equals(purchaseDate, security.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(portfolio, name, category, purchaseDate, purchasePrice, quantity);
    }
}
