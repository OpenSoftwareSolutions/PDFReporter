/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.pdf;

/**
 * Constants for PDF encryption
 * @author donatmuller, 2013, last change 11:53:11 PM
 * 
 */
public interface IEncryption {
	
	/**
	 * Encription key length in bits
	 */
	public enum KeyLength {
		ENCRYPTION_40,
		ENCRYPTION_128		
	};
	
   /** The operation permitted when the document is opened with the user password
    *
    * @since 2.0.7
    */
   public static final int ALLOW_PRINTING = 4 + 2048;

   /** The operation permitted when the document is opened with the user password
    *
    * @since 2.0.7
    */
   public static final int ALLOW_MODIFY_CONTENTS = 8;

   /** The operation permitted when the document is opened with the user password
    *
    * @since 2.0.7
    */
   public static final int ALLOW_COPY = 16;

   /** The operation permitted when the document is opened with the user password
    *
    * @since 2.0.7
    */
   public static final int ALLOW_MODIFY_ANNOTATIONS = 32;

   /** The operation permitted when the document is opened with the user password
    *
    * @since 2.0.7
    */
   public static final int ALLOW_FILL_IN = 256;

   /** The operation permitted when the document is opened with the user password
    *
    * @since 2.0.7
    */
   public static final int ALLOW_SCREENREADERS = 512;

   /** The operation permitted when the document is opened with the user password
    *
    * @since 2.0.7
    */
   public static final int ALLOW_ASSEMBLY = 1024;

   /** The operation permitted when the document is opened with the user password
    *
    * @since 2.0.7
    */
   public static final int ALLOW_DEGRADED_PRINTING = 4;

}
