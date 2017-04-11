package mapMaker.model.tile.riverConfiguration;

import mapMaker.model.tile.Terrain;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RiverConfigurationCyclerTest {

    private RiverConfigurationCycler riverConfigurationCycler;

    @Before
    public void setUp()
    {
        riverConfigurationCycler=new RiverConfigurationCycler(Terrain.MOUNTAIN);
    }

    @Test
    public void getCurrent_beforeNext_noRiver()
    {
        RiverConfiguration rc = riverConfigurationCycler.getCurrent();
        assertEquals(RiverConfiguration.getNoRivers(), rc);
    }

    @Test
    public void getCurrent_6Nexts_properConfigurations()
    {
        RiverConfiguration rc = riverConfigurationCycler.getCurrent();
        assertEquals(RiverConfiguration.getNoRivers(), rc);
        riverConfigurationCycler.next();

        rc = riverConfigurationCycler.getCurrent();
        assertEquals(RiverConfiguration.getSpringHead(), rc);
        riverConfigurationCycler.next();

        rc = riverConfigurationCycler.getCurrent();
        assertEquals(RiverConfiguration.getAdjacentFaces(), rc);
        riverConfigurationCycler.next();

        rc = riverConfigurationCycler.getCurrent();
        assertEquals(RiverConfiguration.getSkipAFace(), rc);
        riverConfigurationCycler.next();

        rc = riverConfigurationCycler.getCurrent();
        assertEquals(RiverConfiguration.getOppositeFaces(), rc);
        riverConfigurationCycler.next();

        rc = riverConfigurationCycler.getCurrent();
        assertEquals(RiverConfiguration.getEveryOtherFace(), rc);
        riverConfigurationCycler.next();
    }

    @Test
    public void getCurrent_6Previous_properConfigurations()
    {
        RiverConfiguration rc = riverConfigurationCycler.getCurrent();
        assertEquals(RiverConfiguration.getNoRivers(), rc);
        riverConfigurationCycler.previous();

        rc = riverConfigurationCycler.getCurrent();
        assertEquals(RiverConfiguration.getEveryOtherFace(), rc);
        riverConfigurationCycler.previous();

        rc = riverConfigurationCycler.getCurrent();
        assertEquals(RiverConfiguration.getOppositeFaces(), rc);
        riverConfigurationCycler.previous();

        rc = riverConfigurationCycler.getCurrent();
        assertEquals(RiverConfiguration.getSkipAFace(), rc);
        riverConfigurationCycler.previous();

        rc = riverConfigurationCycler.getCurrent();
        assertEquals(RiverConfiguration.getAdjacentFaces(), rc);
        riverConfigurationCycler.previous();

        rc = riverConfigurationCycler.getCurrent();
        assertEquals(RiverConfiguration.getSpringHead(), rc);
        riverConfigurationCycler.previous();
    }

    @Test
    public void next_calledOnce_differentConfiguration()
    {
        RiverConfiguration rc = riverConfigurationCycler.getCurrent();
        riverConfigurationCycler.next();

        assertNotEquals(rc, riverConfigurationCycler.getCurrent());
    }

    @Test
    public void previous_calledOnce_differentConfiguration()
    {
        RiverConfiguration rc = riverConfigurationCycler.getCurrent();
        riverConfigurationCycler.previous();

        assertNotEquals(rc, riverConfigurationCycler.getCurrent());
    }
}