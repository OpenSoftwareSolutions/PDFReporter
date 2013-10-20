/*******************************************************************************
 * Copyright 2001-2005 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.oss.pdfreporter.uses.org.apache.java.lang;


/**
 * Static methods for managing byte arrays (all methods follow Big
 * Endian order where most significant bits are in front).
 *
 * @author <a href="mailto:stefano@apache.org">Stefano Mazzocchi</a>
 * @version $Id: Bytes.java,v 1.1.1.1 2001/08/16 05:08:26 jvanzyl Exp $
 */
public class Bytes
{
    private static final char[] hexDigits =
    {
        '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'
    };

    /**
     * Appends two bytes array into one.
     *
     * @param a A byte[].
     * @param b A byte[].
     * @return A byte[].
     */
    public static byte[] append(byte[] a,
                                byte[] b)
    {
        byte[] z = new byte[a.length + b.length];
        System.arraycopy(a, 0, z, 0, a.length);
        System.arraycopy(b, 0, z, a.length, b.length);
        return z;
    }

    /**
     * Appends three bytes array into one.
     *
     * @param a A byte[].
     * @param b A byte[].
     * @param c A byte[].
     * @return A byte[].
     */
    public static byte[] append(byte[] a,
                                byte[] b,
                                byte[] c)
    {
        byte[] z = new byte[a.length + b.length + c.length];
        System.arraycopy(a, 0, z, 0, a.length);
        System.arraycopy(b, 0, z, a.length, b.length);
        System.arraycopy(c, 0, z, a.length + b.length, c.length);
        return z;
    }

    /**
     * Compares two byte arrays for equality.
     *
     * @param a A byte[].
     * @param b A byte[].
     * @return True if the arrays have identical contents.
     */
    public static boolean areEqual(byte[] a,
                                   byte[] b)
    {
        int aLength = a.length;
        if (aLength != b.length) return false;

        for (int i = 0; i < aLength; i++)
            if (a[i] != b[i])
                return false;

        return true;
    }

    /**
     * Gets the end of the byte array given.
     *
     * @param b A byte[].
     * @param pos The position from which to start.
     * @return A byte[] consisting of the portion of b between pos and
     * the end of b.
     */
    public static byte[] copy(byte[] b,
                              int pos)
    {
        return copy(b, pos, b.length - pos);
    }

    /**
     * Gets a sub-set of the byte array given.
     *
     * @param b A byte[].
     * @param pos The position from which to start.
     * @param length The number of bytes to copy from the original
     * byte array to the new one.
     * @return A byte[] consisting of the portion of b starting at pos
     * and continuing for length bytes, or until the end of b is
     * reached, which ever occurs first.
     */
    public static byte[] copy(byte[] b,
                              int pos,
                              int length)
    {
        byte[] z = new byte[length];
        System.arraycopy(b, pos, z, 0, length);
        return z;
    }

    /**
     * Merges a bytes array into another.
     *
     * @param src A byte[].
     * @param dest A byte[].
     */
    public static void merge(byte[] src,
                             byte[] dest)
    {
        System.arraycopy(src, 0, dest, 0, src.length);
    }

    /**
     * Merges a bytes array into another starting from the
     * given position.
     *
     * @param src A byte[].
     * @param dest A byte[].
     * @param pos The position from which to start.
     */
    public static void merge(byte[] src,
                             byte[] dest,
                             int pos)
    {
        System.arraycopy(src, 0, dest, pos, src.length);
    }

    /**
     * Merges a bytes array into another starting from the
     * given position.
     *
     * @param src A byte[].
     * @param dest A byte[].
     * @param pos The position from which to start.
     * @param length The number of bytes to merge.
     */
    public static void merge(byte[] src,
                             byte[] dest,
                             int pos,
                             int length)
    {
        System.arraycopy(src, 0, dest, pos, length);
    }

    /**
     * Merges a bytes array into another starting from the
     * given positions.
     *
     * @param src A byte[].
     * @param dest A byte[].
     * @param srcpos The position from which to start in src.
     * @param destpos The position from which to start in dest.
     * @param length The number of bytes to merge.
     */
    public static void merge(byte[] src,
                             byte[] dest,
                             int srcpos,
                             int destpos,
                             int length)
    {
        System.arraycopy(src, srcpos, dest, destpos, length);
    }

    /**
     * Returns a 4-byte array built from an int.
     *
     * @param n The number to convert.
     * @return A byte[].
     */
    public static byte[] toBytes(int n)
    {
        return toBytes(n, new byte[4]);
    }

    /**
     * Build a 4-byte array from an int.  No check is performed on the
     * array length.
     *
     * @param n The number to convert.
     * @param b The array to fill.
     * @return A byte[].
     */
    public static byte[] toBytes(int n,
                                 byte[] b)
    {
        b[3] = (byte) (n);
        n >>>= 8;
        b[2] = (byte) (n);
        n >>>= 8;
        b[1] = (byte) (n);
        n >>>= 8;
        b[0] = (byte) (n);

        return b;
    }

    /**
     * Returns a 8-byte array built from a long.
     *
     * @param n The number to convert.
     * @return A byte[].
     */
    public static byte[] toBytes(long n)
    {
        return toBytes(n, new byte[8]);
    }

    /**
     * Build a 8-byte array from a long.  No check is performed on the
     * array length.
     *
     * @param n The number to convert.
     * @param b The array to fill.
     * @return A byte[].
     */
    public static byte[] toBytes(long n,
                                 byte[] b)
    {
        b[7] = (byte) (n);
        n >>>= 8;
        b[6] = (byte) (n);
        n >>>= 8;
        b[5] = (byte) (n);
        n >>>= 8;
        b[4] = (byte) (n);
        n >>>= 8;
        b[3] = (byte) (n);
        n >>>= 8;
        b[2] = (byte) (n);
        n >>>= 8;
        b[1] = (byte) (n);
        n >>>= 8;
        b[0] = (byte) (n);

        return b;
    }

    /**
     * Build an int from first 4 bytes of the array.
     *
     * @param b The byte[] to convert.
     * @return An int.
     */
    public static int toInt(byte[] b)
    {
        return ((((int) b[3]) & 0xFF) +
                ((((int) b[2]) & 0xFF) << 8) +
                ((((int) b[1]) & 0xFF) << 16) +
                ((((int) b[0]) & 0xFF) << 24));
    }

    /**
     * Build a long from first 8 bytes of the array.
     *
     * @param b The byte[] to convert.
     * @return A long.
     */
    public static long toLong(byte[] b)
    {
        return ((((long) b[7]) & 0xFF) +
                ((((long) b[6]) & 0xFF) << 8) +
                ((((long) b[5]) & 0xFF) << 16) +
                ((((long) b[4]) & 0xFF) << 24) +
                ((((long) b[3]) & 0xFF) << 32) +
                ((((long) b[2]) & 0xFF) << 40) +
                ((((long) b[1]) & 0xFF) << 48) +
                ((((long) b[0]) & 0xFF) << 56));
    }

    /**
     * Returns a string of hexadecimal digits from a byte array.
     *
     * @param b The byte[] to convert.
     * @return A String.
     */
    public static String toString(byte[] b)
    {
        return toString(b, 0, b.length);
    }

    /**
     * Returns a string of hexadecimal digits from a byte array,
     * starting at offset and continuing for length bytes.
     *
     * @param b The byte[] to convert.
     * @param offset An int.
     * @param length An int.
     * @return A String.
     */
    public static String toString(byte[] b,
                                  int offset,
                                  int length)
    {
        char[] buf = new char[length * 2];

        for (int i = offset, j = 0, k; i < offset + length; i++)
        {
            k = b[i];
            buf[j++] = hexDigits[(k >>> 4) & 0x0F];
            buf[j++] = hexDigits[k & 0x0F];
        }

        return new String(buf);
    }
}
