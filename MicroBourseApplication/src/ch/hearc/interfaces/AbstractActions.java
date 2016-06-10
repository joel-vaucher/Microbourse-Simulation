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

/**
 * 
 */
public abstract class AbstractActions {
        protected long ID;
        
        /**
         * Show the graph
         * @param chart
         */
        public void showChart(LineChart<String, Number> chart) {
            ServicesOffreDao soo = new OffreDaoImplement();
            List<Offre> offresVente = soo.getBestOffersByDay(this.ID, Offre.operationType.VENTE);
            List<Offre> offresAchat = soo.getBestOffersByDay(this.ID, Offre.operationType.ACHAT);

            XYChart.Series achatSeries = new XYChart.Series();
            XYChart.Series venteSeries = new XYChart.Series();
            achatSeries.setName("Achat");
            venteSeries.setName("Vente");

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            
            for(Offre offre : offresAchat) { 
                String offreDate = offre.getDate() == null ? currentDate : offre.getDate().toString();
                achatSeries.getData().add(new XYChart.Data(offreDate, offre.getPrix()));
            }
            
            for(Offre offre : offresVente) {
                String offreDate = offre.getDate() == null ? currentDate : offre.getDate().toString(); 
                venteSeries.getData().add(new XYChart.Data(offreDate, offre.getPrix()));
            }

            // Affichage des trois series
            chart.getData().addAll(achatSeries, venteSeries);
        }
        
        /**
         * Effectue une vente immédiate
         * @param nbAction
         */
        public void buyAP(int nbAction) {
            if(nbAction == 0) return;
            
            Long idA = AppController.getIdUser();
            
            ServicesActionnaireDAO sao = new ActionnaireDaoImplement();
            ServicesEntrepriseDao seo = new EntrepriseDaoImplement();
            ServicesOffreDao soo = new OffreDaoImplement();
            List<Offre> offres = soo.getCurrentPurchaseOffersByEntreprise(this.ID);
            
            int nbActionVendu = 0;
            int iOffre = 0;
            while(nbActionVendu < nbAction) {
                int q = offres.get(iOffre).getQuantite();
                if(nbActionVendu + q > nbAction){
                    soo.sellImmediat(offres.get(iOffre), idA, q - (nbAction-nbActionVendu));
                    nbActionVendu = nbAction;
                } else {
                    soo.sellImmediat(offres.get(iOffre), idA, q);
                    nbActionVendu += q;
                }
                iOffre++;
            }    
        }
        
        /**
         * Effectue une vente immédiate
         * @param nbAction
         */
        public void saleAP(int nbAction) {
            if(nbAction == 0) return;
            
            Long idA = AppController.getIdUser();
            
            ServicesActionnaireDAO sao = new ActionnaireDaoImplement();
            ServicesEntrepriseDao seo = new EntrepriseDaoImplement();
            ServicesOffreDao soo = new OffreDaoImplement();
            
            List<Offre> offres = soo.getCurrentSellOffersByEntreprise(this.ID);
            
            int nbActionAchetee = 0;
            int iOffre = 0;
            while(nbActionAchetee < nbAction) {
                int q = offres.get(iOffre).getQuantite();
                if(nbActionAchetee + q > nbAction){
                    soo.buyImmediat(offres.get(iOffre), idA, nbAction);
                    nbActionAchetee = nbAction;
                } else {
                    soo.buyImmediat(offres.get(iOffre), idA, q);
                    nbActionAchetee += q;
                }
            }   
        }
        
        /**
         * Effectue une vente selon un prix
         * @param nbAction
         * @param prix
         */
        public void sale(int nbAction, int prix) {
            if(nbAction == 0 || nbAction == 0) return;
            
            Long idA = AppController.getIdUser();
            
            ServicesActionnaireDAO sao = new ActionnaireDaoImplement();
            ServicesEntrepriseDao seo = new EntrepriseDaoImplement();
            ServicesOffreDao soo = new OffreDaoImplement();
            
            soo.sellOffer(idA, this.ID, nbAction, prix);
        }
        
        /**
         * Effectue un achat selon un prix
         * @param nbAction
         * @param prix
         */
        public void buy(int nbAction, int prix) {
            if(nbAction == 0 || nbAction == 0) return;
            
            Long idA = AppController.getIdUser();
            
            ServicesActionnaireDAO sao = new ActionnaireDaoImplement();
            ServicesEntrepriseDao seo = new EntrepriseDaoImplement();
            ServicesOffreDao soo = new OffreDaoImplement();
            
            soo.buyOffer(idA, this.ID, nbAction, prix); 
        }

}
