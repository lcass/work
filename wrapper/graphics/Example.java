package wrapper.graphics;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

import static org.lwjgl.util.glu.GLU.*;
import static org.lwjgl.opengl.GL11.*;


public class Example {
	public Example(){
		start();
		try {
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   glMatrixMode(GL_PROJECTION);
	        glLoadIdentity();
	        gluPerspective(30f, 1080f / 720f, 0.001f, 1000f);
	        glMatrixMode(GL_MODELVIEW);
	        glEnable(GL_DEPTH_TEST);
	}
	public static void main(String[] args){
		new Example();
	}
	public void start(){
	
		  
	}
}
