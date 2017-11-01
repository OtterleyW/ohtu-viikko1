package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenTilavuusLuoKayttokelvettomanVaraston(){
        Varasto huonoVarasto = new Varasto(-1);
        assertEquals(0, huonoVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void luoKuormitettuVarasto(){
        Varasto v = new Varasto(10, 5);
        assertEquals(10, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(5, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void luoKuormitettuKayttokelvotonVarasto(){
        Varasto v = new Varasto(-5, 5);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void luoKuormitettuVarastoNegatiivinenSaldo() {
        Varasto v = new Varasto(5, -1);
        assertEquals(1, v.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test    
    public void laitaVarastoonLiikaa(){
        varasto.lisaaVarastoon(10);
        varasto.lisaaVarastoon(2);
        
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void laitaNegatiivinenMaara() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otaLiikaaKunVarastoTyhja(){
       assertEquals(0, varasto.otaVarastosta(2), vertailuTarkkuus);
    }
    
    @Test
    public void otaLiikaaKunVarastossaTavaraa(){
        varasto.lisaaVarastoon(4);
       assertEquals(4, varasto.otaVarastosta(8), vertailuTarkkuus);
    }
    
    @Test
    public void otaNegatiivinenMaara(){
        assertEquals(0, varasto.otaVarastosta(-1), vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii(){
        varasto.lisaaVarastoon(2);
        assertEquals("saldo = 2.0, vielä tilaa 8.0", varasto.toString());
    }

}