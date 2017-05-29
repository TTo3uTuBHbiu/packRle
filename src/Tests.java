/**
 * Created by z on 29.05.2017.
 */


import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class Tests {
    @Test
    public void encode() {
        assertEquals("shdsd(*3k)s", packRLE.compression("shdsdkkks"));
        assertEquals("sd(*4a)", packRLE.compression("sdaaaa"));
        assertEquals("(*9r)(*5r)", packRLE.compression("rrrrrrrrrrrrrr"));
        assertEquals("(*5a)(*3e)", packRLE.compression("aaaaaeee"));
        assertEquals("(*9e)(*3e)", packRLE.compression("eeeeeeeeeeee"));
        assertEquals("(*4r)", packRLE.compression("rrrr"));
    }
    @Test
    public void decode() {
        assertEquals("shdsdkkks", packRLE.decode("shdsd(*3k)s"));
        assertEquals("sdaaaa", packRLE.decode("sd(*4a)"));
        assertEquals("rrrrrrrrrrrrrr",packRLE.decode("(*9r)(*5r)"));
        assertEquals("aaaaaeee", packRLE.decode("(*5a)(*3e)"));
        assertEquals("eeeeeeeeeeee", packRLE.decode("(*9e)(*3e)"));
        assertEquals("rrrr", packRLE.decode("(*4r)"));
    }
}

