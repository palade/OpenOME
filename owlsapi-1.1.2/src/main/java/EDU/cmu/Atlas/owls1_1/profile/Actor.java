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
package EDU.cmu.Atlas.owls1_1.profile;

import EDU.cmu.Atlas.owls1_1.core.OWLS_Object;
/**
 * @author Naveen Srinivasan
 */
public interface Actor extends OWLS_Object {

    public String getName();

    public void setName(String name);

    /**
     * extract the title of the contact
     * @return a string with the title, or null if undefined
     */
    public String getTitle();

    /**
     * set the title of the contact
     * @param title a string
     */
    public void setTitle(String title);

    public String getPhone();

    public void setPhone(String phone);

    public String getFax();

    public void setFax(String fax);

    public String getEmail();

    public void setEmail(String email);

    public String getAddress();

    public void setAddress(String Address);

    public String getWebURL();

    public void setWebURL(String webURL);

}

