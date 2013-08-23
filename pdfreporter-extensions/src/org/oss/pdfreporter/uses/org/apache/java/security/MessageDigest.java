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
 * This interface abstracts a message digest algorithm.
 *
 * <p><b>Note:</b> even if standard Java 1.1 APIs already provide a
 * message digest implementation, this class is used on those Java
 * runtime environments (like Kaffe) where the package
 * <code>java.security</code> is highly improbable to be found.
 *
 * @author <a href="mailto:mazzocchi@mbox.systemy.it">Stefano Mazzocchi</a>
 * @version $Id: MessageDigest.java,v 1.1.1.1 2001/08/16 05:08:27 jvanzyl Exp $
 */
public abstract class MessageDigest
{
    /**
     * Creates the algorithm and reset its state.
     */
    public MessageDigest()
    {
        this.reset();
    }

    /**
     * Append another block to the message.
     *
     * @param block A byte[].
     */
    public void append(byte[] block)
    {
        this.append(block, 0, block.length);
    }

    /**
     * Append another block of specified length to the message.
     *
     * @param block A byte[].
     * @param length An int.
     */
    public void append(byte[] block,
                       int length)
    {
        this.append(block, 0, length);
    }

    /**
     * Append another block of specified length to the message
     * starting at the given offset.
     *
     * @param block A byte[].
     * @param offset An int.
     * @param length An int.
     */
    public abstract void append(byte[] block,
                                int offset,
                                int length);

    /**
     * Appends a message block and return its message digest.
     *
     * @param block A byte[].
     * @return A byte[].
     */
    public byte[] digest(byte[] block)
    {
        return this.digest(block, 0, block.length);
    }

    /**
     * Appends a message block with specified length and return its
     * message digest.
     *
     * @param block A byte[].
     * @param length An int.
     * @return A byte[].
     */
    public byte[] digest(byte[] block,
                         int length)
    {
        return this.digest(block, 0, length);
    }

    /**
     * Appends a message block with specified length starting from the
     * given offset and return its message digest.
     *
     * @param block A byte[].
     * @param offset An int.
     * @param length An int.
     * @return A byte[].
     */
    public abstract byte[] digest(byte[] block,
                                  int offset,
                                  int length);

    /**
     * Resets the state of the class.  <b>Beware</b>: calling this
     * method erases all data previously inserted.
     */
    public abstract void reset();
}
