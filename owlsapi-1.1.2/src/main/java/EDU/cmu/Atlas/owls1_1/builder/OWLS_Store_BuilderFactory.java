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
package EDU.cmu.Atlas.owls1_1.builder;

import EDU.cmu.Atlas.owls1_1.builder.implementation.OWLS_Store_BuilderImpl;

/**
 * Factory class for OWLS_Store_Builder
 * @author Naveen Srinivasan
 * @see EDU.cmu.Atlas.owls1_1.builder.OWLS_Object_Builder
 */
public class OWLS_Store_BuilderFactory {

    private static OWLS_Store_Builder storeBuilder;

    /**
     * Get an instance of OWLS_Store_Builder
     * @return instance of OWLS_Store_Builder
     */

    public static OWLS_Store_Builder instance() {

        if (storeBuilder == null) {
            storeBuilder = new OWLS_Store_BuilderImpl();
        }
        return storeBuilder;
    }
}