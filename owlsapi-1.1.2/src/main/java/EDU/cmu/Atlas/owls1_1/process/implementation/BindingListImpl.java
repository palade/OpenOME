/*
 * OWL-S API provides functionalities to create and to manipulate OWL-S files. Copyright
 * (C) 2005 Katia Sycara, Softagents Lab, Carnegie Mellon University
 * 
 * This library is free software; you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation; either version
 * 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with this library;
 * if not, write to the
 * 
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA
 * 
 * The Intelligent Software Agents Lab The Robotics Institute Carnegie Mellon University 5000 Forbes
 * Avenue Pittsburgh PA 15213
 * 
 * More information available at http://www.cs.cmu.edu/~softagents/
 */
package EDU.cmu.Atlas.owls1_1.process.implementation;

import EDU.cmu.Atlas.owls1_1.core.implementation.OWLS_StoreImpl;
import EDU.cmu.Atlas.owls1_1.process.Binding;
import EDU.cmu.Atlas.owls1_1.process.BindingList;

/**
 * @author Massimo Paolucci
 */
public class BindingListImpl extends OWLS_StoreImpl implements BindingList {

    /**
     * add a binding to the list
     * @param binding the binding to add
     */
    public void addBinding(Binding binding) {
        add(binding);
    }

    /**
     * remove a binding from the list
     * @param binding the binding to remove
     */
    public boolean removeBinding(Binding binding) {
        return remove(binding);
    }

    /**
     * function that gives access to the binding stored
     * @param index the index of the binding to look at
     * @return the binding found
     * @throws IndexOutOfRangeException when index does not point to a Profile
     *             in the DB
     */
    public Binding getNthBindingDescription(int index) throws IndexOutOfBoundsException {
        return (Binding) getNth(index);
    }

    public boolean removeBinding(String uri) {
        return remove(uri);
    }

    public Binding getBinding(String uri) {
        return (Binding) get(uri);
    }
}