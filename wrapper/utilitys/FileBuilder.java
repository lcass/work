//Author , Lcass / Lucas Spencer
//This software is designed to function as a wrapper for specific LWJGL/OpenGL bindings.
//The software wraps certain vbo creation functions and allows sprite batching methods.
// Copyright (C)  2015  Lucas Spencer
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.
package wrapper.utilitys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileBuilder {
	 public static String gettext(String name)
	    {
	        StringBuilder source = new StringBuilder();
	        try
	        {
	            BufferedReader reader = new BufferedReader(new FileReader(new File(name)));
	            
	            String line;
	            while ((line = reader.readLine()) != null)
	            {
	                source.append(line).append("\n");
	            }
	            
	            reader.close();
	        }
	        catch (Exception e)
	        {
	            System.err.println("Error loading source code: " + name);
	            e.printStackTrace();
	          
	        }
	        
	        return source.toString();
	    }
}
