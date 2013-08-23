package org.oss.pdfreporter.uses.org.apache.digester;

import org.oss.pdfreporter.xml.parsers.IAttributes;


/**
 * <p> Interface for use with {@link FactoryCreateRule}.
 * The rule calls {@link #createObject} to create an object
 * to be pushed onto the <code>Digester</code> stack
 * whenever it is matched.</p>
 *
 * <p> {@link AbstractObjectCreationFactory} is an abstract
 * implementation suitable for creating anonymous
 * <code>ObjectCreationFactory</code> implementations.
 */
public interface IObjectCreationFactory {

    /**
     * <p>Factory method called by {@link FactoryCreateRule} to supply an
     * object based on the element's attributes.
     *
     * @param attributes the element's attributes
     *
     * @throws Exception any exception thrown will be propagated upwards
     */
    public Object createObject(IAttributes attributes) throws Exception;

    /**
     * <p>Returns the {@link NotImplementedDigester} that was set by the
     * {@link FactoryCreateRule} upon initialization.
     */
    public IDigester getDigester();

    /**
     * <p>Set the {@link NotImplementedDigester} to allow the implementation to do logging,
     * classloading based on the digester's classloader, etc.
     *
     * @param digester parent Digester object
     */
    public void setDigester(IDigester digester);

}
