package ch.hearc.daoimplement;

import ch.hearc.databasefactory.DataBaseConnection;
import ch.hearc.exception.DatabaseException;
import ch.hearc.metiers.Action;
import ch.hearc.metiers.Actionnaire;
import ch.hearc.metiers.Offre;
import ch.hearc.servicesdao.ServicesActionDAO;
import ch.hearc.servicesdao.ServicesEntrepriseDao;
import ch.hearc.servicesdao.ServicesOffreDao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charlesombangndo
 */
public class OffreDaoImplement implements ServicesOffreDao{
    
    public OffreDaoImplement (){
        
    }

    @Override
    public void createOffre(Offre offre) {
        PreparedStatement state = null;
        Connection conn = null;
        ResultSet result = null;
        try{
            conn = DataBaseConnection.getDataBase().getConnection();
            String query = "INSERT INTO Offres(quantite, prix, statut, operations, date_echange, fk_entreprise_2,fk_actionnaire_offre,fk_actionnaire_op_im) VALUES(?,?,?,?,?,?,?,?)";
            state = conn.prepareStatement(query, new String[]{"ID"});
            state.setInt(1, offre.getQuantite());
            state.setDouble(2, offre.getPrix());
            state.setInt(3, offre.getStatut().ordinal());
            state.setInt(4, offre.getOperation().ordinal());
            if(offre.getDate() == null){
                state.setNull(5, Types.DATE);
            } else {
                state.setDate(5, offre.getDate());
            }
            state.setLong(6, offre.getIdEntreprise());
            state.setLong(7, offre.getIdActionnaireOffre());
            if(offre.getIdActionnaireOpIm() == null){
                state.setNull(8, Types.INTEGER);
            } else {
                state.setLong(8, offre.getIdActionnaireOpIm());
            }
            
            
            state.executeUpdate();
            DataBaseConnection.getDataBase().commit();
            ResultSet rs = state.getGeneratedKeys();
            rs.next();
            Long lastId = rs.getLong(1);
            offre.setIdOffre(lastId);
        }catch(SQLException ex){
            ex.getMessage();
          
        } catch (DatabaseException ex) {
            Logger.getLogger(OffreDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Offre getOffreByID(Long idOffre) {
        
        Statement state = null;
        Connection conn = null;
        ResultSet result = null;
        Offre offre = null;
        try{
            conn = DataBaseConnection.getDataBase().getConnection();
            state = conn.createStatement();
            String query = "SELECT ID FORM OFFRES WHERE ID = idOffre";
            result = state.executeQuery(query);
            
            while(result.next()){
                Long idOf = result.getLong("ID");
                int quantite = result.getInt("QUANTITE");
                double price = result.getDouble("PRIX");
                Offre.statusType status = Offre.statusType.values()[result.getInt("STATUT")];
                Offre.operationType  operation= Offre.operationType.values()[result.getInt("OPERATIONS")];
                Date date = result.getDate("DATE_ECHANGE");
                Long idEntre = result.getLong("FK_ENTREPRISE_2");
                Long idAc_of = result.getLong("FK_ACTIONNAIRE_OFFRE");
                Long idAc_op = result.getLong("FK_ACTIONNAIRE_OP_IM");
                offre = new Offre(idOf,quantite,price,status,operation,date,idEntre,idAc_of,idAc_op);
            }     
        }catch(SQLException ex){
            ex.getMessage();
        }catch(DatabaseException ex){
            Logger.getLogger(OffreDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return offre;
    }

    @Override
    public void updateOffre(Offre offre) {
        PreparedStatement state = null;
        Connection conn = null;
        ResultSet result = null;
        try{
            conn = DataBaseConnection.getDataBase().getConnection();
            String query = "UPDATE Offres SET quantite=?, prix=?, statut=?, operations=?, date_echange=?, fk_entreprise_2=?,fk_actionnaire_offre=?,fk_actionnaire_op_im=? WHERE id = ?";
            state = conn.prepareStatement(query);
            state.setInt(1, offre.getQuantite());
            state.setDouble(2, offre.getPrix());
            state.setInt(3, offre.getStatut().ordinal());
            state.setInt(4, offre.getOperation().ordinal());
            if(offre.getDate() == null){
                state.setNull(5, Types.DATE);
            } else {
                state.setDate(5, offre.getDate());
            }
            state.setLong(6, offre.getIdEntreprise());
            state.setLong(7, offre.getIdActionnaireOffre());
            if(offre.getIdActionnaireOpIm() == null){
                state.setNull(8, Types.INTEGER);
            } else {
                state.setLong(8, offre.getIdActionnaireOpIm());
            }
            state.setLong(9, offre.getIdOffre());
            
            
            state.executeUpdate();
            DataBaseConnection.getDataBase().commit();
        }catch(SQLException ex){
            ex.getMessage();
          
        } catch (DatabaseException ex) {
            Logger.getLogger(OffreDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteOffre(Offre offre) {
        
        PreparedStatement state = null;
        Connection conn = null;
        ResultSet result = null;
        try{
            conn = DataBaseConnection.getDataBase().getConnection();
            String query = "DELETE FROM Offres WHERE id = ?";
            state = conn.prepareStatement(query);
            state.setLong(1, offre.getIdOffre());
            state.executeUpdate();
            DataBaseConnection.getDataBase().commit();
        }catch(SQLException ex){
            ex.getMessage();
        } catch (DatabaseException ex) {
            Logger.getLogger(OffreDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Offre> getCurrentSellOffersByEntreprise(Long idE) {
        
        PreparedStatement state = null;
        Connection conn = null;
        ResultSet result = null;
        List<Offre> offres = new ArrayList<>();
        try{
            conn = DataBaseConnection.getDataBase().getConnection();
            String query = String.format("SELECT * FROM OFFRES WHERE statut = %d AND operations = %d AND fk_entreprise_2 = ?", Offre.statusType.EN_COURS.ordinal(), Offre.operationType.VENTE.ordinal());
            state = conn.prepareStatement(query);
            state.setLong(1, idE);
            result = state.executeQuery();
            while(result.next()){
                Long id = result.getLong("ID");
                int quantite = result.getInt("QUANTITE");
                double prix = result.getDouble("PRIX");
                Offre.statusType status = Offre.statusType.values()[result.getInt("STATUT")];
                Offre.operationType operation = Offre.operationType.values()[result.getInt("OPERATIONS")];
                Date date = result.getDate("DATE_ECHANGE");
                Long idActionnaireOffre = result.getLong("FK_ACTIONNAIRE_OFFRE");
                Long idActionnaireOPIM = result.getLong("FK_ACTIONNAIRE_OP_IM");
                if(idActionnaireOPIM == 0){
                    idActionnaireOPIM = null;
                }
                Long idEntreprise = result.getLong("FK_ENTREPRISE_2");
                offres.add(new Offre(id,quantite,prix,status,operation,date,idActionnaireOffre,idActionnaireOPIM,idEntreprise));
            }
        }catch(SQLException ex){
            ex.getMessage();
          
        } catch (DatabaseException ex) {
            Logger.getLogger(OffreDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return offres;
    }

    @Override
    public List<Offre> getCurrentPurchaseOffersByEntreprise(Long idE) {
        
        PreparedStatement state = null;
        Connection conn = null;
        ResultSet result = null;
        List<Offre> offres = new ArrayList<>();
        try{
            conn = DataBaseConnection.getDataBase().getConnection();
            String query = String.format("SELECT * FROM OFFRES WHERE statut = %d AND operations = %d AND fk_entreprise_2 = ?", Offre.statusType.EN_COURS.ordinal(), Offre.operationType.ACHAT.ordinal());
            state = conn.prepareStatement(query);
            state.setLong(1, idE);
            result = state.executeQuery();
            while(result.next()){
                Long id = result.getLong("ID");
                int quantite = result.getInt("QUANTITE");
                double prix = result.getDouble("PRIX");
                Offre.statusType status = Offre.statusType.values()[result.getInt("STATUT")];
                Offre.operationType operation = Offre.operationType.values()[result.getInt("OPERATIONS")];
                Date date = result.getDate("DATE_ECHANGE");
                Long idActionnaireOffre = result.getLong("FK_ACTIONNAIRE_OFFRE");
                Long idActionnaireOPIM = result.getLong("FK_ACTIONNAIRE_OP_IM");
                if(idActionnaireOPIM == 0){
                    idActionnaireOPIM = null;
                }
                Long idEntreprise = result.getLong("FK_ENTREPRISE_2");
                offres.add(new Offre(id,quantite,prix,status,operation,date,idActionnaireOffre,idActionnaireOPIM,idEntreprise));
            }
        }catch(SQLException ex){
            ex.getMessage();
          
        } catch (DatabaseException ex) {
            Logger.getLogger(OffreDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return offres;
    }

    @Override
    public void buyImmediat(Offre o, Long idA, int quantite) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        Offre historiqueTransaction;
        historiqueTransaction = new Offre(null, quantite, o.getPrix(), Offre.statusType.FINI, Offre.operationType.VENTE, new Date(c.getTimeInMillis()), o.getIdActionnaireOffre(), idA, o.getIdEntreprise());
        createOffre(historiqueTransaction);
        
        if(o.getQuantite() <= quantite){
            deleteOffre(o);
        } else {
            o.setQuantite(o.getQuantite() - quantite);
            updateOffre(o);
        }

        ServicesEntrepriseDao seo = new EntrepriseDaoImplement();
        ServicesActionDAO saco = new ActionDaoImplement();
        
        Action acVendeur = saco.getActionOfActionnaireByEnterprise(historiqueTransaction.getIdActionnaireOffre(), historiqueTransaction.getIdEntreprise());
        acVendeur.setQuantite(acVendeur.getQuantite()-historiqueTransaction.getQuantite());
        Action acAcheteur = saco.getActionOfActionnaireByEnterprise(historiqueTransaction.getIdActionnaireOpIm(), historiqueTransaction.getIdEntreprise());
        acAcheteur.setQuantite(acAcheteur.getQuantite()+historiqueTransaction.getQuantite());
        
        saco.updateAction(acVendeur);
        saco.updateAction(acAcheteur);
    }

    @Override
    public void sellImmediat(Offre o, Long idA, int quantite) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        Offre historiqueTransaction;
        historiqueTransaction = new Offre(null, quantite, o.getPrix(), Offre.statusType.FINI, Offre.operationType.ACHAT, new Date(c.getTimeInMillis()), o.getIdActionnaireOffre(), idA, o.getIdEntreprise());
        createOffre(historiqueTransaction);
        
        if(o.getQuantite() <= quantite){
            deleteOffre(o);
        } else {
            o.setQuantite(o.getQuantite() - quantite);
            updateOffre(o);
        }
        ServicesEntrepriseDao seo = new EntrepriseDaoImplement();
        ServicesActionDAO saco = new ActionDaoImplement();
        
        Action acVendeur = saco.getActionOfActionnaireByEnterprise(historiqueTransaction.getIdActionnaireOpIm(), historiqueTransaction.getIdEntreprise());
        acVendeur.setQuantite(acVendeur.getQuantite()+historiqueTransaction.getQuantite());
        Action acAcheteur = saco.getActionOfActionnaireByEnterprise(historiqueTransaction.getIdActionnaireOffre(), historiqueTransaction.getIdEntreprise());
        acAcheteur.setQuantite(acAcheteur.getQuantite()-historiqueTransaction.getQuantite());
        
        saco.updateAction(acVendeur);
        saco.updateAction(acAcheteur);
    }

    @Override
    public void buyOffer(Long idA, Long idE, int nbAction, int prix) {
        Offre newOffer = new Offre(null, nbAction, prix, Offre.statusType.EN_COURS, Offre.operationType.ACHAT, null, idA, null, idE);
        createOffre(newOffer);
    }

    @Override
    public void sellOffer(Long idA, Long idE, int nbAction, int prix) {
        Offre newOffer = new Offre(null, nbAction, prix, Offre.statusType.EN_COURS, Offre.operationType.VENTE, null, idA, null, idE);
        createOffre(newOffer);
    }

}
