package TheBusiness.SolutionManagement;

import TheBusiness.SolutionManagement.SolutionOffer;

/**
 *
 * This class will extract summary data from the solution offer.
 */
public class SolutionOfferSummary {

    SolutionOffer subjectSolutionOffer;
    int numberOfSalesAboveTarget;
    int numberOfSalesBelowTarget;
    double solutionPricePerformance; // total profit above target -- could be negative too
    double actualSalesVolume;
    int rank; // will be done later

    public SolutionOfferSummary(SolutionOffer offer) {
        subjectSolutionOffer = offer; // keeps track of the solution offer itself
        numberOfSalesAboveTarget = offer.getNumberOfProductSalesAboveTarget();
        solutionPricePerformance = offer.getOrderPricePerformance();
        actualSalesVolume = offer.getSalesVolume();
        numberOfSalesBelowTarget = offer.getNumberOfProductSalesBelowTarget();
    }

    public double getSalesRevenues() {
        return actualSalesVolume;
    }

    public double getNumberAboveTarget() {
        return numberOfSalesAboveTarget;
    }

    public double getSolutionPricePerformance() {
        return solutionPricePerformance;
    }

    public int getNumberBelowTarget() {
        return numberOfSalesBelowTarget;
    }

    public boolean isSolutionAlwaysAboveTarget() {
        return false; // to be implemented
    }
}
