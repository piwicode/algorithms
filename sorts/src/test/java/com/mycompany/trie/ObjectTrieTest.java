/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trie;

import com.google.common.base.Charsets;
import com.google.common.collect.Sets;
import com.google.common.io.CharStreams;
import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class ObjectTrieTest {

    public ObjectTrieTest() {
    }

    @Test
    public void testSomeMethod() {
        final ObjectTri tri = new ObjectTri();
        Assert.assertFalse(tri.contains("abc"));
        Assert.assertFalse(tri.toCompactTrie().contains("abc"));
        Assert.assertFalse(tri.contains(""));
        Assert.assertFalse(tri.toCompactTrie().contains(""));
        tri.add("");
        Assert.assertTrue(tri.contains(""));
        Assert.assertTrue(tri.toCompactTrie().contains(""));
        tri.add("abcd");
        Assert.assertFalse(tri.contains("a"));
        Assert.assertFalse(tri.toCompactTrie().contains("a"));
        Assert.assertFalse(tri.contains("b"));
        Assert.assertFalse(tri.toCompactTrie().contains("b"));
        Assert.assertFalse(tri.contains("ab"));
        Assert.assertFalse(tri.toCompactTrie().contains("ab"));
        Assert.assertTrue(tri.contains("abcd"));
        Assert.assertTrue(tri.toCompactTrie().contains("abcd"));
    }

    @Test
    public void testData() throws IOException {
        final URL url = Resources.getResource(ObjectTrieTest.class, "dictionary.txt");
        final List<String> words = CharStreams.readLines(Resources.asCharSource(url, Charsets.UTF_8));
        final HashSet<String> hm = Sets.newHashSetWithExpectedSize(words.size());
        final ObjectTri tri = new ObjectTri();
        for (String word : words) {
            assertTrue(tri.add(word));
            assertTrue(hm.add(word));
        }

        Random rnd = new Random();

        for (String word : words) {
            assertTrue(tri.contains(word));

            assertTrue(hm.contains(word));
            final int p = rnd.nextInt(word.length());
            String notWord = word.substring(0, p) + Character.toUpperCase(word.charAt(p)) + word.substring(p + 1, word.length());
            assertFalse(tri.contains(notWord));
            assertFalse(hm.contains(notWord));
        }

    }

    @Test
    public void testCompact() throws IOException {
        final URL url = Resources.getResource(ObjectTrieTest.class, "dictionary.txt");
        final List<String> words = CharStreams.readLines(Resources.asCharSource(url, Charsets.UTF_8));

        final ObjectTri tri = new ObjectTri();
        for (String word : words) {
            assertTrue(tri.add(word));
        }
        final CompactTri ctri = tri.toCompactTrie();
        final Random rnd = new Random();
        for (int i = 0; i < 100; i++) {
            for (String word : words) {
                assertTrue(ctri.contains(word));
                final int p = rnd.nextInt(word.length());
                String notWord = word.substring(0, p) + Character.toUpperCase(word.charAt(p)) + word.substring(p + 1, word.length());
                assertFalse(ctri.contains(notWord));
            }
        }
    }

}
