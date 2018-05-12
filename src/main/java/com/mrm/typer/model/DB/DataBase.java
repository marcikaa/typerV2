package com.mrm.typer.model.DB;

import com.mrm.typer.model.entity.JPAEntity;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ez az osztály felelős az adatbáziskapcsolat irányításáért.
 * @author marcikaa
 */
@Slf4j
public class DataBase {
    //CHECKSTYLE:OFF
    private static Logger logger = LoggerFactory.getLogger(DataBase.class);
    private static final DataBase DB_PELDANY = new DataBase();
    
    @PersistenceContext(unitName = "typerDB")
    private EntityManager em;
    
    private DataBase() {
    }
    //CHECKSTYLE:ON
    /**
     * Visszaadja az adatbázis példányát.
     * @return referencia a példányra
     */
    public static DataBase getDbPeldany() {
        return DB_PELDANY;
    }
    
    /**
     * Entitymanager megnyitása (adatbáziskapcsolat).
     *
     * @throws Exception JPA hiba esetén
     */
    public void connectDB() throws Exception {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("typerDB");
        em = emFactory.createEntityManager();
        logger.trace("Adatbázis kapcsolat OK.");
    }
    
    /**
     * Entitymanager lezárása.
     */
    public void disconnectDB() {
        if (connected()) {
            em.close();
            logger.trace("Disconnect OK.");
        }
        em = null;
    }
    
    /**
     * Megnézi hogy az em létezik és nyitva van-e.
     * @return nyitva van-e
     */
    public boolean connected() {
        
        return em != null && em.isOpen();
    }
    
  /**
     * @param entity menteni kíván entitás
     *
     * @return mentett entitás (nem lenne feltétlenül szükséges, lehetne akár
     * void is, viszont hibaellenőrzéshez tök jó szerintem)
     *
     * @throws IllegalStateException ha nincs adatbázis-kapcsolat
     * @throws IllegalArgumentException ha a menteni kívánt film címe
     * érvénytelen ({@code null})
     * @throws Exception JPA hiba esetén
     */
    public JPAEntity save(JPAEntity entity) throws IllegalStateException, IllegalArgumentException, Exception {

        if (!connected()) {
            throw new IllegalStateException("Nincs adatbázis-kapcsolat!");
        }

        if (entity == null) {
            throw new IllegalArgumentException("A mentendő entitás null!");
        }

        try {
            em.getTransaction().begin();

            if (entity.getId() == null) {
                em.persist(entity);  //új entitás --> persist (insert)
            } else {
                em.merge(entity);    //módosítás --> merge (update)
            }

            em.getTransaction().commit();

            return entity;
        } catch (PersistenceException e) {

            log.error("JPA lekérdezési hiba!");
            throw new Exception("JPA hiba!", e);
        }
    }
    
    /**
     * Kitöröl egy entitást az adatbázisból.
     *
     * @param entity törlendő entitás
     *
     * @throws IllegalStateException ha nincs adatbázis-kapcsolat
     * @throws IllegalArgumentException ha a törlendő entitás null vagy nincs
     * {@code ID}-ja
     * @throws Exception JPA hiba esetén
     */
    public void delete(JPAEntity entity) throws IllegalStateException, IllegalArgumentException, Exception {
        if (!connected()) {
            throw new IllegalStateException("Nincs adatbázis-kapcsolat!");
        }
        
        if (entity == null || entity.getId() == 0) {
            throw new IllegalArgumentException("A törlendő entitás null vagy nincs ID-je!");
        }
        
        try {
            //a törlés előtt kikeresem az entitást, hogy biztosan Managed legyen
            JPAEntity delEntity = em.find(JPAEntity.class, entity.getId());
            
            if (delEntity.getId() == null) {
                throw new IllegalArgumentException("A törlendő film nem található az adatbázisban!");
            }
            
            em.getTransaction().begin();
            em.remove(delEntity);
            em.getTransaction().commit();
            
        } catch (PersistenceException e) {
            logger.error("JPA lekérdezési hiba!" + e);
        }
    }
    
    
    /**
     * {@code ID} alapján player-t tudunk keresni.
     *
     * @param id player példány {@code ID}-ja
     *
     * @return a keresett JPAEntity példány, vagy {@code null}, ha nem
     * található az adatbázisban
     *
     * @throws IllegalStateException ha nincs adatbázis-kapcsolat
     * @throws Exception JPA hiba esetén
     */
    public JPAEntity findPlayerByID(Long id) throws IllegalStateException, Exception {
        
        if (!connected()) {
            throw new IllegalStateException("Nincs adatbázis-kapcsolat!");
        }
        
        if (id == null) {
            return null;
        }
        
        try {
            
            Query query = em.createNamedQuery("JPAEntity.findPlayerByID");
            query.setParameter("id", id);
            JPAEntity entity = (JPAEntity) query.getSingleResult();
            
            return entity;
            
        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            logger.error("JPA lekérdezési hiba!");
            throw new Exception("JPA hiba!", e);
        }
    }
    
    /**
     * Player keresése {@code playerName}név alapján.
     *
     * @param playerName a keresendő player neve
     *
     * @return JPAEntity entitás példányok, vagy {@code null}, ha nem
     * található az adatbázisban
     *
     * @throws IllegalStateException ha nincs adatbázis-kapcsolat
     * @throws IllegalArgumentException ha a keresendő player neve érvénytelen
     * (üres vagy {@code null})
     * @throws Exception JPA hiba esetén
     */
    public List<JPAEntity> findPlayerByName(String playerName) throws IllegalStateException, IllegalArgumentException, Exception {
        
        if (!connected()) {
            throw new IllegalStateException("Nincs adatbázis-kapcsolat!");
        }
        
        if (StringUtils.isEmpty(playerName)) {
            throw new IllegalArgumentException("Érvénytelen a cím megadása!");
        }
        
        try {
            Query query = em.createNamedQuery("JPAEntity.findPlayerByName", JPAEntity.class);
            query.setParameter("playerName", playerName);
            
            @SuppressWarnings("unchecked")
                    List<JPAEntity> entitys = query.getResultList();
            
            return entitys;
            
        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            logger.error("JPA lekérdezési hiba!");
            throw new Exception("JPA hiba!", e);
        }
    }
    
    /**
     * Nevek és pontok listázása.
     * @return egy listával ami tárolja az összes nevet és pontot
     * @throws IllegalStateException ha nincs adatbázis kapcsolat
     */
    public List<JPAEntity> getAllOrderedByScore() throws IllegalStateException {
        
        if (!connected()) {
            throw new IllegalStateException("Nincs adatbázis-kapcsolat!");
        }
        
        return (List<JPAEntity>) em.createNamedQuery("JPAEntity.getAllOrderedByScore").getResultList();
    }
}
