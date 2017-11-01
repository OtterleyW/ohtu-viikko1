/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jenni
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void haePelaaja(){
        Player p = stats.search("Semenko");
        assertEquals("Semenko", p.getName());
    }
    
        @Test
    public void pelaajaaEiLoydy() {
        Player p = stats.search("Laine");
        assertNull(p);
    }
    
    @Test
    public void haeJoukkue() {
        List<Player> j = stats.team("EDM");
        assertEquals(3, j.size());
    }
    
    @Test
    public void haeOikeaMaaraTopScorers() {
        List<Player> s = stats.topScorers(3);
        assertEquals(3, s.size());
    }
}
