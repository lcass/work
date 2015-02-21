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
package wrapper.graphics;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glDetachShader;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;
import wrapper.utilitys.FileBuilder;

public class Shader {
	public int programID;
    
    int vertexShaderID;
    int fragmentShaderID;
    int geometryShaderID = -1;
    /**
     * Set the shader up
     */
    public Shader()
    {
        programID = glCreateProgram();
    }
    
    /**
     * Bind a new Vertex Shader to the program
     * @param name The location of the vertex shader
     */
    public void attachVertexShader(String name)
    {
        String vertexShaderSource = FileBuilder.gettext(name);
        vertexShaderID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShaderID, vertexShaderSource);
        glCompileShader(vertexShaderID);
        if (glGetShaderi(vertexShaderID, GL_COMPILE_STATUS) == GL_FALSE)
        {
            System.err.println("Unable to create vertex shader:");
            dispose();
           
        }
        glAttachShader(programID, vertexShaderID);
    }
    /**
     * Bind a new Fragment Shader to the program.
     * @param name The location of the fragment shader
     */
    public void attachFragmentShader(String name)
    {
        String fragmentShaderSource = FileBuilder.gettext(name);
        fragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShaderID, fragmentShaderSource);
        glCompileShader(fragmentShaderID);
        if (glGetShaderi(fragmentShaderID, GL_COMPILE_STATUS) == GL_FALSE)
        {
            System.err.println("Unable to create fragment shader:");
            dispose();
        }
        glAttachShader(programID, fragmentShaderID);
    }
    /**
     * Bind a new Geometry Shader to the program.
     * @param name The location of the Geometry shader
     */
    public void attachGeometryShader(String name)
    {
       
        String geometryShaderSource = FileBuilder.gettext(name);
        geometryShaderID = glCreateShader(GL_GEOMETRY_SHADER);
        glShaderSource(geometryShaderID, geometryShaderSource);

        glCompileShader(geometryShaderID);

        if (glGetShaderi(geometryShaderID, GL_COMPILE_STATUS) == GL_FALSE)
        {
            System.err.println("Unable to create Geometry shader:");
            dispose();

        }

        glAttachShader(programID, geometryShaderID);
    }
    
    /** 
     * Link the shader program to the GPU , must be done before attaching to a Vbo
     */
    public void link()
    {
        glLinkProgram(programID);
        if (glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE)
        {
            System.err.println("Unable to link shader program:");
            dispose();
           
        }
    }
    /**
     * Bind the current program to the gpu
     */
    public void bind()
    {
        glUseProgram(programID);
    }
    /**
     * Unbind the current program from the gpu
     */
    public static void unbind()
    {
        glUseProgram(0);
    }
    /**
     * Destroy the shader and dispose of all current data of it. Must be called on program exit.
     */
    public void dispose()
    {
        unbind();
        glDetachShader(programID, vertexShaderID);
        glDetachShader(programID, fragmentShaderID);
        glDeleteShader(vertexShaderID);
        glDeleteShader(fragmentShaderID);
        if(geometryShaderID != -1){
        	glDeleteShader(geometryShaderID);
        }
        glDeleteProgram(programID);
    }
}
