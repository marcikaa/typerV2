//package com.mrm.typer.model;
//
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.NoResultException;
//import javax.persistence.Persistence;
//import javax.persistence.PersistenceException;
//import javax.persistence.Query;
//import lombok.extern.slf4j.Slf4j;
////import org.apache.commons.lang3.StringUtils;
//
///**
// * JPA adatbázis-kapcsolatot megvalósító osztály. Itt fejtjük ki többek között a Queryket is.
// * Az adatbáziskapcsolatot a Main-ben érdemes létrehozni. Startkor kapcsolódni a
// * DB-hez, majd bezárás előtt lejönni róla.
// * (Megteheted, hogy minden adatbázis-műveletkor commentelsz, majd lejössz, de ez nyílván a program lassúságát okozza,
// * hiszen ez sok adatbázis-fogalmat generál.)
// *
// * Létrehozása mainben:
// *
// *public class Main { 
// *  private static final SampleDB DB = SampleDB.getDbPeldany();
// *
// *  public static void main(String[] args) {
// *      DB.connectDB();
// *
// *      ...main...
// *
// *      DB.disconnectDB();
// *  }
// *}
// *
// * persistence.xml az src/main/META-INF alá kell, hogy kerüljön.
// *
// * @author balint
// */
//@Slf4j
//public class SampleDB {
//
//    private static final SampleDB DB_PELDANY = new SampleDB();
//
//    private EntityManager em;
//
//    /**
//     * Privát konstruktor.
//     */
//    private SampleDB() {
//    }
//
//    /**
//     * Aktiális DB szingleton példány lekérése.
//     *
//     * @return singleton példány referencia
//     */
//    public static SampleDB getDbPeldany() {
//        return DB_PELDANY;
//    }
//
//    /**
//     * Adatbáziskapcsolat létrehozása JPA-val.
//     *
//     * @throws Exception JPA hiba esetén
//     */
//    public void connectDB() throws Exception {
//        //persistence.xml-ben fontos, hogy megegyezzen a persistence-unit name ezzel, jelen esetben 'database'
//        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("database");
//        em = emFactory.createEntityManager();
//        log.trace("Adatbázis kapcsolat OK.");
//    }
//
//    /**
//     * Adatbáziskapcsolat lezárása JPA-val.
//     */
//    public void disconnectDB() {
//        if (connected()) {
//            em.close();
//            log.trace("Disconnect OK.");
//        }
//        em = null;
//    }
//
//    /**
//     * EntityManager él és csatlakoztatva van az adatbázishoz?
//     *
//     * @return true -> igen
//     */
//    public boolean connected() {
//
//        return em != null && em.isOpen();
//    }
//
//    /**
//     * Entitás mentése az adatbázisba.
//     *  - Ha a menteni kívánt entitás ID-ja
//     *    {@code null}, biztosan új entitást kell létrehozni -> persist kell
//     *
//     *  - Ha a menteni kívánt entitás ID-ja NEM {@code null}, akkor ez egy
//     *    Managed vagy Detached entitás -> merge kell
//     *
//     * (ha ezeket nem érted nincs baj, JPA-nak nézz picit utána vagy majd elmagyarázom :D)
//     *
//     * @param entity menteni kíván entitás
//     *
//     * @return mentett entitás (nem lenne feltétlenül szükséges, lehetne akár void is,
//     *                          viszont hibaellenőrzéshez tök jó szerintem)
//     *
//     * @throws IllegalStateException ha nincs adatbázis-kapcsolat
//     * @throws IllegalArgumentException ha a menteni kívánt film címe
//     *                                  érvénytelen ({@code null})
//     * @throws Exception JPA hiba esetén
//     */
//    public SampleJPAEntity save(SampleJPAEntity entity) throws IllegalStateException, IllegalArgumentException, Exception {
//
//        if (!connected()) {
//            throw new IllegalStateException("Nincs adatbázis-kapcsolat!");
//        }
//
//        if (entity == null) {
//            throw new IllegalArgumentException("A mentendő entitás null!");
//        }
//
//        try {
//            em.getTransaction().begin();
//
//            if (entity.getId() == null) {
//                em.persist(entity);  //új entitás --> persist (insert)
//            } else {
//                em.merge(entity);    //módosítás --> merge (update)
//            }
//
//            em.getTransaction().commit();
//
//            return entity;
//        } catch (PersistenceException e) {
//
//            log.error("JPA lekérdezési hiba!");
//            throw new Exception("JPA hiba!", e);
//        }
//    }
//
//    /**
//     * Entitás törlése az adatbázisból.
//     *
//     * @param entity törlendő entitás
//     *
//     * @throws IllegalStateException ha nincs adatbázis-kapcsolat
//     * @throws IllegalArgumentException ha a törlendő entitás null vagy nincs
//     *                                  {@code ID}-ja
//     * @throws Exception JPA hiba esetén
//     */
//    public void delete(SampleJPAEntity entity) throws IllegalStateException, IllegalArgumentException, Exception {
//        if (!connected()) {
//            throw new IllegalStateException("Nincs adatbázis-kapcsolat!");
//        }
//
//        if (entity == null || entity.getId() == 0) {
//            throw new IllegalArgumentException("A törlendő entitás null vagy nincs ID-je!");
//        }
//
//        try {
//            //a törlés előtt kikeresem az entitást, hogy biztosan Managed legyen
//            SampleJPAEntity delEntity = em.find(SampleJPAEntity.class, entity.getId());
//
//            if (delEntity.getId() == null) {
//                throw new IllegalArgumentException("A törlendő film nem található az adatbázisban!");
//            }
//
//            em.getTransaction().begin();
//            em.remove(delEntity);
//            em.getTransaction().commit();
//
//        } catch (PersistenceException e) {
//            log.error("JPA lekérdezési hiba!");
//            throw new Exception("JPA hiba", e);
//        }
//    }
//
////---------------------------------------------------------
////---------------------NamedQueries------------------------
////---------------------------------------------------------
//
//    /**
//     * Player keresése ID alapján.
//     *
//     * @param id player példány {@code ID}-ja
//     *
//     * @return a keresett SampleJPAEntity példány, vagy {@code null}, ha nem
//     * található az adatbázisban
//     *
//     * @throws IllegalStateException ha nincs adatbázis-kapcsolat
//     * @throws Exception JPA hiba esetén
//     */
//    public SampleJPAEntity findPlayerByID(Long id) throws IllegalStateException, Exception {
//
//        if (!connected()) {
//            throw new IllegalStateException("Nincs adatbázis-kapcsolat!");
//        }
//
//        if (id == null) {
//            return null;
//        }
//
//        try {
//            Query query = em.createNamedQuery("SampleJPAEntity.findPlayerByID");
//            query.setParameter("id", id);
//            SampleJPAEntity entity = (SampleJPAEntity) query.getSingleResult();
//
//            return entity;
//
//        } catch (NoResultException e) {
//            return null;
//        } catch (PersistenceException e) {
//            log.error("JPA lekérdezési hiba!");
//            throw new Exception("JPA hiba!", e);
//        }
//    }
//
//    /**
//     * Player(ek) keresése név alapján.
//     *
//     * @param playerName a keresendő player(ek) neve --> (megteheted, hogy az adatbázisban egy player csak egyszer szerepeljen)
//     *                                                   (lehet default is így van, erre fejből nem emlékszem, az itt leírtak nincsenek tesztelve, csak példák)
//     *                                                   (ha nem is engedi, max dob egy exceptiont a JPA, rá fogsz jönni... :) )
//     *
//     * @return SampleJPAEntity entitás példányok, vagy {@code null}, ha nem
//     * található az adatbázisban
//     *
//     * @throws IllegalStateException ha nincs adatbázis-kapcsolat
//     * @throws IllegalArgumentException ha a keresendő player neve érvénytelen
//     * (üres vagy {@code null})
//     * @throws Exception JPA hiba esetén
//     */
//    public List<SampleJPAEntity> findPlayerByName(String playerName) throws IllegalStateException, IllegalArgumentException, Exception {
//
//        if (!connected()) {
//            throw new IllegalStateException("Nincs adatbázis-kapcsolat!");
//        }
//
//        if (StringUtils.isEmpty(playerName)) {
//            throw new IllegalArgumentException("Érvénytelen a cím megadása!");
//        }
//
//        try {
//            Query query = em.createNamedQuery("SampleJPAEntity.findPlayerByName", SampleJPAEntity.class);
//            query.setParameter("playerName", playerName);
//            
////A "...uses unchecked or unsafe operations" warning elkerülése, itt csak SampleJPAEntity lista jöhet vissza
//            @SuppressWarnings("unchecked")
//            List<SampleJPAEntity> entitys = query.getResultList();
//
//            return entitys;
//
//        } catch (NoResultException e) {
//            return null;
//        } catch (PersistenceException e) {
//            log.error("JPA lekérdezési hiba!");
//            throw new Exception("JPA hiba!", e);
//        }
//    }
//
//    /**
//     * High score(ok) keresése. Lehet több ugyan olyan score is, ezért List.
//     *
//     * @return SampleJPAEntity entitás példányok, vagy {@code null}, ha nem
//     * található az adatbázisban
//     * @throws IllegalStateException ha nincs adatbázis-kapcsolat
//     * @throws Exception JPA hiba esetén
//     */
//    public List<SampleJPAEntity> findHighScore() throws IllegalStateException, Exception {
//        
//        if (!connected()) {
//            throw new IllegalStateException("Nincs adatbázis-kapcsolat!");
//        }
//
//        try {
//            Query query = em.createNamedQuery("SampleJPAEntity.findHighScore", SampleJPAEntity.class);
//
//            @SuppressWarnings("unchecked")
//            List<SampleJPAEntity> entitys = query.getResultList();
//
//            return entitys;
//
//        } catch (NoResultException e) {
//            return null;
//        } catch (PersistenceException e) {
//            log.error("JPA lekérdezési hiba!");
//            throw new Exception("JPA hiba!", e);
//        }
//    }
//}
