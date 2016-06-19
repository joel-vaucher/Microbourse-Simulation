package ch.hearc.interfaces;

import ch.hearc.daoimplement.ActionnaireDaoImplement;
import ch.hearc.daoimplement.EntrepriseDaoImplement;
import ch.hearc.daoimplement.OffreDaoImplement;
import ch.hearc.metiers.Offre;
import ch.hearc.servicesdao.ServicesActionnaireDAO;
import ch.hearc.servicesdao.ServicesEntrepriseDao;
import ch.hearc.servicesdao.ServicesOffreDao;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

/**
 * Cette classe permet de réunir les fonctionnalités
 * nécessaires aux classes TuileController et DetailController qui partage
 * plusieures fonctionnalités.
 */
public abstract class AbstractActions {
    protected long ID;

    /**
     * Récupération et traitemement des données reçues depuis la BDD
     * afin de créer les courbes affichées sur le graphique
     * @param chart : Graphique qui sera remplit
     */
    public void showChart(LineChart<String, Number> chart) {
        // Récupération des données
        ServicesOffreDao soo = new OffreDaoImplement();
        List<Offre> offresVente = soo.getBestOffersByDay(this.ID, Offre.operationType.VENTE);
        List<Offre> offresAchat = soo.getBestOffersByDay(this.ID, Offre.operationType.ACHAT);

        // Nettoyage du graphique
        chart.getData().clear();

        // Création des deux courbes (Achats et Ventes)
        XYChart.Series achatSeries = new XYChart.Series();
        XYChart.Series venteSeries = new XYChart.Series();
        achatSeries.setName("Achat");
        venteSeries.setName("Vente");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentDate = dateFormat.format(date);

        // Préparation de la courbe représentant les achats
        for(Offre offre : offresAchat) { 
            String offreDate = offre.getDate() == null ? currentDate : offre.getDate().toString();
            achatSeries.getData().add(new XYChart.Data(offreDate, offre.getPrix()));
        }

        // Préparation de la courbe représentant les ventes
        for(Offre offre : offresVente) {
            String offreDate = offre.getDate() == null ? currentDate : offre.getDate().toString(); 
            venteSeries.getData().add(new XYChart.Data(offreDate, offre.getPrix()));
        }

        // Affectation des deux courbes au graphe
        chart.getData().addAll(achatSeries, venteSeries);
    }

    /**
     * Effectue une vente immédiate
     * @param nbAction : Nombre d'actions pour vendre
     */
    public void saleAP(int nbAction) {
        if(nbAction == 0) return;

        Long idA = AppController.getUserID();

        ServicesActionnaireDAO sao = new ActionnaireDaoImplement();
        ServicesEntrepriseDao seo = new EntrepriseDaoImplement();
        ServicesOffreDao soo = new OffreDaoImplement();
        List<Offre> offres = soo.getCurrentPurchaseOffersByEntreprise(this.ID);

        int nbActionVendu = 0;
        int iOffre = 0;
        while(nbActionVendu < nbAction) {
            int q = offres.get(iOffre).getQuantite();
            if(nbActionVendu + q > nbAction){
                soo.sellImmediat(offres.get(iOffre), idA, nbAction-nbActionVendu);
                nbActionVendu = nbAction;
            } else {
                soo.sellImmediat(offres.get(iOffre), idA, q);
                nbActionVendu += q;
            }
            iOffre++;
        }    
    }

    /**
     * Effectue un achat immédiat
     * @param nbAction : Nombre d'actions à acheter
     */
    public void buyAP(int nbAction) {
        if(nbAction == 0) return;

        Long idA = AppController.getUserID();

        ServicesActionnaireDAO sao = new ActionnaireDaoImplement();
        ServicesEntrepriseDao seo = new EntrepriseDaoImplement();
        ServicesOffreDao soo = new OffreDaoImplement();

        List<Offre> offres = soo.getCurrentSellOffersByEntreprise(this.ID);

        int nbActionAchetee = 0;
        int iOffre = 0;
        while(nbActionAchetee < nbAction) {
            int q = offres.get(iOffre).getQuantite();
            if(nbActionAchetee + q > nbAction){
                soo.buyImmediat(offres.get(iOffre), idA, nbAction-nbActionAchetee);
                nbActionAchetee = nbAction;
            } else {
                soo.buyImmediat(offres.get(iOffre), idA, q);
                nbActionAchetee += q;
            }
        }   
    }

    /**
     * Effectue une vente selon un prix
     * @param nbAction  : Nombre d'actions pour vendre
     * @param prix      : Prix de vente
     */
    public void sale(int nbAction, int prix) {
        if(nbAction == 0 || nbAction == 0) return;

        Long idA = AppController.getUserID();

        ServicesActionnaireDAO sao = new ActionnaireDaoImplement();
        ServicesEntrepriseDao seo = new EntrepriseDaoImplement();
        ServicesOffreDao soo = new OffreDaoImplement();

        soo.sellOffer(idA, this.ID, nbAction, prix);
    }

    /**
     * Effectue un achat  selon un prix
     * @param nbAction  : Nombre d'actions à acheter
     * @param prix      : Prix d'achat
     */
    public void buy(int nbAction, int prix) {
        if(nbAction == 0 || nbAction == 0) return;

        Long idA = AppController.getUserID();

        ServicesActionnaireDAO sao = new ActionnaireDaoImplement();
        ServicesEntrepriseDao seo = new EntrepriseDaoImplement();
        ServicesOffreDao soo = new OffreDaoImplement();

        soo.buyOffer(idA, this.ID, nbAction, prix); 
    }
        
    /**
     * Affichage des prix actuels
     */
    public void showPrices(Label lblPriceBuy, Label lblPriceSale) {
        // Récupération des données
        ServicesOffreDao soo = new OffreDaoImplement();
        List<Offre> offresVente = soo.getBestOffersByDay(this.ID, Offre.operationType.VENTE);
        List<Offre> offresAchat = soo.getBestOffersByDay(this.ID, Offre.operationType.ACHAT);
        
        // Affichage des données
        if(!offresAchat.isEmpty()) {
            lblPriceBuy.setText(offresAchat.get(offresAchat.size()-1).getPrix()+"");
        }
        if(!offresVente.isEmpty()) {
            lblPriceSale.setText(offresVente.get(offresVente.size()-1).getPrix()+"");
        }
    }

}
