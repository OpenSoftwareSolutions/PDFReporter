package org.oss.pdfreporter.uses.org.apache.java.security;

/* ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Apache" and "Apache Software Foundation" and 
 *    "Apache Turbine" must not be used to endorse or promote products 
 *    derived from this software without prior written permission. For 
 *    written permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    "Apache Turbine", nor may "Apache" appear in their name, without 
 *    prior written permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */

/**
 * This class implements the Message Digest 5 algorithm (MD5) as
 * defined in RFC-1321.
 *
 * <p><b>Note:</b> even if standard Java 1.1 APIs already provide a
 * MD5 implementation, this class is used on those Java runtime
 * environments (like Kaffe) where the package
 * <code>java.security</code> is highly improbable to be found.
 *
 * @author <a href="mailto:stefano@apache.org">Stefano Mazzocchi</a>
 * @version $Id: MD5.java,v 1.1.1.1 2001/08/16 05:08:27 jvanzyl Exp $
 */
public final class MD5
    extends MessageDigest
{
    private long counter;
    private int reminder;
    private byte buffer[];
    private int state[];
    private int x[];

    /*********************** MD5 Functions ***********************/

    // 16 * 4 bytes
    static byte padding[] =
    {
        (byte) 0x80,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    };

    /*********************** Self Test ***********************/

    private static String[] messages =
    {
        "",
        "a",
        "abc",
        "message digest",
        "abcdefghijklmnopqrstuvwxyz",
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789",
        "12345678901234567890123456789012345678901234567890123456789012345678901234567890",
    };

    private static String[] digests =
    {
        "d41d8cd98f00b204e9800998ecf8427e",
        "0cc175b9c0f1b6a831c399e269772661",
        "900150983cd24fb0d6963f7d28e17f72",
        "f96b697d7cb7938d525a2f31aaf161d0",
        "c3fcd3d76192e4007dfb496cca67e13b",
        "d174ab98d277d9f5a5611c2c9f419d9f",
        "57edf4a22be3c955ac49da2e2107b67a",
    };


    /**
     * Creates the algorithm and reset its state.
     */
    public MD5()
    {
        super();
    }

    /**
     * Append another block of specified length to the message
     * starting at the given offset.
     *
     * @param block A byte[].
     * @param offset An int.
     * @param length An int.
     */
    public void append(byte[] block,
                       int offset,
                       int length)
    {
        while (true)
        {
            if (length >= reminder)
            {
                System.arraycopy(block, offset, buffer,
                                 (int) (counter & 63L), reminder);
                transform(buffer);
                counter += reminder;
                offset += reminder;
                length -= reminder;
                reminder = 64;
            }
            else
            {
                System.arraycopy(block, offset, buffer,
                                 (int) (counter & 63L), length);
                counter += length;
                reminder -= length;
                break;
            }
        }
    }

    /*********************** Byte/Int utilities ***********************/

    /**
     * Converts a 64-byte array into a 16-int array.
     *
     * @param in A byte[].
     * @param out An int[].
     */
    private static void byte2int(byte[] in,
                                 int[] out)
    {
        for (int inpos = 0, outpos = 0; outpos < 16; outpos++)
        {
            out[outpos] = ((((int) (in[inpos++] & 0xff))) |
                           (((int) (in[inpos++] & 0xff)) << 8) |
                           (((int) (in[inpos++] & 0xff)) << 16) |
                           (((int) (in[inpos++] & 0xff)) << 24));
        }
    }

    /**
     * Appends a message block with specified length starting from the
     * given offset, and return its message digest.
     *
     * @param block A byte[].
     * @param offset An int.
     * @param length An int.
     */
    public byte[] digest(byte[] block,
                         int offset,
                         int length)
    {
        this.append(block, offset, length);

        byte[] bits = toBytes(counter << 3);
        byte[] digest = new byte[16];

        if (reminder > 8)
        {
            append(padding, 0, reminder - 8);
        }
        else
        {
            append(padding, 0, 64 + (reminder - 8));
        }

        append(bits, 0, 8);

        int2byte(state, digest);

        this.reset();
        return digest;
    }

    /*
     * Method F.
     *
     * @param x An int.
     * @param y An int.
     * @param z An int.
     * @return An int.
     */
    static private int F(int x,
                         int y,
                         int z)
    {
        return (z ^ (x & (y^z)));
    }

    /*
     * Method FF.
     *
     * @param a An int.
     * @param b An int.
     * @param c An int.
     * @param d An int.
     * @param x An int.
     * @param s An int.
     * @param ac An int.
     * @return An int.
     */
    static private int FF(int a,
                          int b,
                          int c,
                          int d,
                          int x,
                          int s,
                          int ac)
    {
        a += x + ac + F(b,c,d);
        a = (a << s | a >>> -s);
        return a + b;
    }

    /*
     * Method G.
     *
     * @param x An int.
     * @param y An int.
     * @param z An int.
     * @return An int.
     */
    static private int G(int x,
                         int y,
                         int z)
    {
        return (y ^ (z & (x^y)));
    }

    /*
     * Method GG.
     *
     * @param a An int.
     * @param b An int.
     * @param c An int.
     * @param d An int.
     * @param x An int.
     * @param s An int.
     * @param ac An int.
     * @return An int.
     */
    static private int GG(int a,
                          int b,
                          int c,
                          int d,
                          int x,
                          int s,
                          int ac)
    {
        a += x + ac + G(b,c,d);
        a = (a << s | a >>> -s);
        return a + b;
    }

    /*
     * Method H.
     *
     * @param x An int.
     * @param y An int.
     * @param z An int.
     * @return An int.
     */
    static private int H(int x,
                         int y,
                         int z)
    {
        return (x ^ y ^ z);
    }

    /*
     * Method HH.
     *
     * @param a An int.
     * @param b An int.
     * @param c An int.
     * @param d An int.
     * @param x An int.
     * @param s An int.
     * @param ac An int.
     * @return An int.
     */
    static private int HH(int a,
                          int b,
                          int c,
                          int d,
                          int x,
                          int s,
                          int ac)
    {
        a += x + ac + H(b,c,d);
        a = (a << s | a >>> -s);
        return a + b;
    }

    /*
     * Method I.
     *
     * @param x An int.
     * @param y An int.
     * @param z An int.
     * @return An int.
     */
    static private int I(int x,
                         int y,
                         int z)
    {
        return (y ^ (x | ~z));
    }

    /*
     * Method II.
     *
     * @param a An int.
     * @param b An int.
     * @param c An int.
     * @param d An int.
     * @param x An int.
     * @param s An int.
     * @param ac An int.
     * @return An int.
     */
    static private int II(int a,
                          int b,
                          int c,
                          int d,
                          int x,
                          int s,
                          int ac)
    {
        a += x + ac + I(b,c,d);
        a = (a << s | a >>> -s);
        return a + b;
    }

    /**
     * Converts a 4-int array into a 16-byte array.
     *
     * @param in An int[].
     * @param out A byte[].
     */
    private static void int2byte(int[] in,
                                 byte[] out)
    {
        for (int inpos = 0, outpos = 0; inpos < 4; inpos++)
        {
            out[outpos++] = (byte) (in[inpos] & 0xff);
            out[outpos++] = (byte) ((in[inpos] >>> 8) & 0xff);
            out[outpos++] = (byte) ((in[inpos] >>> 16) & 0xff);
            out[outpos++] = (byte) ((in[inpos] >>> 24) & 0xff);
        }
    }

    /**
     * Resets the state of the class.  <b>Beware</b>: calling this
     * method erases all data previously inserted.
     */
    public void reset()
    {
        buffer = new byte[64];
        state = new int[4];
        x = new int[16];

        state[0] = 0x67452301;
        state[1] = 0xefcdab89;
        state[2] = 0x98badcfe;
        state[3] = 0x10325476;

        counter = 0;
        reminder = 64;
    }

    /**
     * Converts a long to a 8-byte array using low order first.
     *
     * @param n A long.
     * @return A byte[].
     */
    public static byte[] toBytes(long n)
    {
        byte[] b = new byte[8];

        b[0] = (byte) (n);
        n >>>= 8;
        b[1] = (byte) (n);
        n >>>= 8;
        b[2] = (byte) (n);
        n >>>= 8;
        b[3] = (byte) (n);
        n >>>= 8;
        b[4] = (byte) (n);
        n >>>= 8;
        b[5] = (byte) (n);
        n >>>= 8;
        b[6] = (byte) (n);
        n >>>= 8;
        b[7] = (byte) (n);

        return b;
    }

    /*
     * TODO: Document.
     *
     * @param buffer A byte[].
     */
    private void transform(byte[] buffer)
    {
        int a, b, c, d;

        byte2int(buffer, x);

        a = state[0];
        b = state[1];
        c = state[2];
        d = state[3];

        a = FF(a, b, c, d, x[ 0],  7, 0xd76aa478);
        d = FF(d, a, b, c, x[ 1], 12, 0xe8c7b756);
        c = FF(c, d, a, b, x[ 2], 17, 0x242070db);
        b = FF(b, c, d, a, x[ 3], 22, 0xc1bdceee);
        a = FF(a, b, c, d, x[ 4],  7, 0xf57c0faf);
        d = FF(d, a, b, c, x[ 5], 12, 0x4787c62a);
        c = FF(c, d, a, b, x[ 6], 17, 0xa8304613);
        b = FF(b, c, d, a, x[ 7], 22, 0xfd469501);
        a = FF(a, b, c, d, x[ 8],  7, 0x698098d8);
        d = FF(d, a, b, c, x[ 9], 12, 0x8b44f7af);
        c = FF(c, d, a, b, x[10], 17, 0xffff5bb1);
        b = FF(b, c, d, a, x[11], 22, 0x895cd7be);
        a = FF(a, b, c, d, x[12],  7, 0x6b901122);
        d = FF(d, a, b, c, x[13], 12, 0xfd987193);
        c = FF(c, d, a, b, x[14], 17, 0xa679438e);
        b = FF(b, c, d, a, x[15], 22, 0x49b40821);

        a = GG(a, b, c, d, x[ 1],  5, 0xf61e2562);
        d = GG(d, a, b, c, x[ 6],  9, 0xc040b340);
        c = GG(c, d, a, b, x[11], 14, 0x265e5a51);
        b = GG(b, c, d, a, x[ 0], 20, 0xe9b6c7aa);
        a = GG(a, b, c, d, x[ 5],  5, 0xd62f105d);
        d = GG(d, a, b, c, x[10],  9,  0x2441453);
        c = GG(c, d, a, b, x[15], 14, 0xd8a1e681);
        b = GG(b, c, d, a, x[ 4], 20, 0xe7d3fbc8);
        a = GG(a, b, c, d, x[ 9],  5, 0x21e1cde6);
        d = GG(d, a, b, c, x[14],  9, 0xc33707d6);
        c = GG(c, d, a, b, x[ 3], 14, 0xf4d50d87);
        b = GG(b, c, d, a, x[ 8], 20, 0x455a14ed);
        a = GG(a, b, c, d, x[13],  5, 0xa9e3e905);
        d = GG(d, a, b, c, x[ 2],  9, 0xfcefa3f8);
        c = GG(c, d, a, b, x[ 7], 14, 0x676f02d9);
        b = GG(b, c, d, a, x[12], 20, 0x8d2a4c8a);

        a = HH(a, b, c, d, x[ 5],  4, 0xfffa3942);
        d = HH(d, a, b, c, x[ 8], 11, 0x8771f681);
        c = HH(c, d, a, b, x[11], 16, 0x6d9d6122);
        b = HH(b, c, d, a, x[14], 23, 0xfde5380c);
        a = HH(a, b, c, d, x[ 1],  4, 0xa4beea44);
        d = HH(d, a, b, c, x[ 4], 11, 0x4bdecfa9);
        c = HH(c, d, a, b, x[ 7], 16, 0xf6bb4b60);
        b = HH(b, c, d, a, x[10], 23, 0xbebfbc70);
        a = HH(a, b, c, d, x[13],  4, 0x289b7ec6);
        d = HH(d, a, b, c, x[ 0], 11, 0xeaa127fa);
        c = HH(c, d, a, b, x[ 3], 16, 0xd4ef3085);
        b = HH(b, c, d, a, x[ 6], 23,  0x4881d05);
        a = HH(a, b, c, d, x[ 9],  4, 0xd9d4d039);
        d = HH(d, a, b, c, x[12], 11, 0xe6db99e5);
        c = HH(c, d, a, b, x[15], 16, 0x1fa27cf8);
        b = HH(b, c, d, a, x[ 2], 23, 0xc4ac5665);

        a = II(a, b, c, d, x[ 0],  6, 0xf4292244);
        d = II(d, a, b, c, x[ 7], 10, 0x432aff97);
        c = II(c, d, a, b, x[14], 15, 0xab9423a7);
        b = II(b, c, d, a, x[ 5], 21, 0xfc93a039);
        a = II(a, b, c, d, x[12],  6, 0x655b59c3);
        d = II(d, a, b, c, x[ 3], 10, 0x8f0ccc92);
        c = II(c, d, a, b, x[10], 15, 0xffeff47d);
        b = II(b, c, d, a, x[ 1], 21, 0x85845dd1);
        a = II(a, b, c, d, x[ 8],  6, 0x6fa87e4f);
        d = II(d, a, b, c, x[15], 10, 0xfe2ce6e0);
        c = II(c, d, a, b, x[ 6], 15, 0xa3014314);
        b = II(b, c, d, a, x[13], 21, 0x4e0811a1);
        a = II(a, b, c, d, x[ 4],  6, 0xf7537e82);
        d = II(d, a, b, c, x[11], 10, 0xbd3af235);
        c = II(c, d, a, b, x[ 2], 15, 0x2ad7d2bb);
        b = II(b, c, d, a, x[ 9], 21, 0xeb86d391);

        state[0] += a;
        state[1] += b;
        state[2] += c;
        state[3] += d;
    }
}
