/*
 * Copyright 2018 Márton Szabó.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mrm.typer.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "SCORES", schema = "MY_SCHEMA_NAME")

@NamedQueries({
    @NamedQuery(name = "JPAEntity.findPlayerByID", query = "SELECT e FROM JPAEntity e WHERE e.id = :id ORDER BY e.score")
    ,
    @NamedQuery(name = "JPAEntity.findPlayerByName", query = "SELECT e FROM JPAEntity e WHERE LOWER(e.playerName) LIKE LOWER(:playerName) ORDER BY e.score")
    ,
    @NamedQuery(name = "JPAEntity.findHighScore", query = "SELECT MAX(e.score) FROM JPAEntity e")
})
@Data               //fontos, JPA használja a Gettereket, Settereket
@EqualsAndHashCode  //mégfontosabb, hiszen a JPA ezeken keresztül tud összehasonlítani 2 entitást
@ToString(callSuper = true)
public class JPAEntity implements Serializable {

    /**
     * Entitás elsődleges, automatikusan generált kulcsa. (kötelező JPA elem)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)          // az itt zárójelben megadott 'strategy'-nek amúgy ez a default értéke is, csak szemléltetés miatt írtam ide,
    @Column(name = "ID", nullable = false, updatable = false)   // hiszen a JPA tud saját magától is egyedi ID-t generálni
    private Long id;

    /**
     * Játékos neve.
     */
    @Column(name = "PLAYER_NAME")
    private String playerName;

    /**
     * Játékoshoz tartozó elért pontszám.
     */
    @Column(name = "SCORE")
    private Integer score;

}

