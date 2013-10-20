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
package org.oss.pdfreporter.uses.org.apache.java.security;


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
