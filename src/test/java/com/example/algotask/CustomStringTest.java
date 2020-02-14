package com.example.algotask;

import org.junit.Assert;
import org.junit.Test;

public class CustomStringTest {

    @Test
    public void search_empty_test() {
        CustomString c = new CustomString("abc".toCharArray());
        Assert.assertFalse(c.search(""));
    }

    @Test
    public void search_a_in_abc_test() {
        CustomString c = new CustomString("abc".toCharArray());
        Assert.assertTrue(c.search("a"));
        Assert.assertTrue(c.search("ab"));
        Assert.assertTrue(c.search("abc"));
        Assert.assertTrue(c.search("bc"));
        Assert.assertTrue(c.search("b"));
        Assert.assertTrue(c.search("c"));
        Assert.assertFalse(c.search("d"));
    }

    @Test
    public void repl_test() {
        CustomString c = new CustomString("abc".toCharArray());
        CustomString n = c.replace("b", "z");
        Assert.assertEquals("azc", n.toString());

        CustomString c2 = new CustomString("abc".toCharArray());
        CustomString n2 = c2.replace("ab", "z");
        Assert.assertEquals("zc", n2.toString());

        CustomString c3 = new CustomString("abc".toCharArray());
        CustomString n3 = c3.replace("abc", "z");
        Assert.assertEquals("z", n3.toString());

        CustomString c4 = new CustomString("abc".toCharArray());
        CustomString n4 = c4.replace("c", "z");
        Assert.assertEquals("abz", n4.toString());
    }


}
