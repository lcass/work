package wrapper.graphics;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import wrapper.utilitys.Vertex3d;

public class Example {
	private Shader shader;
	private Binder binder;

	public Example() {

		try {

			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.sync(60);
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GL11.glViewport(0, 0, 800, 600);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(30, 800.0f/600.0f, 1, 1000);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
		shader = new Shader();
		shader.attachFragmentShader("./src/shaders/default_frag.frag");
		shader.attachVertexShader("./src/shaders/default_vert.vert");
		shader.link();
		float z = -1;
		binder = new Binder(shader, 800, 600, true);
		binder.bind_vertices(new Vertex3d[] { new Vertex3d(0, 0, z),
				new Vertex3d(1f, 1, z), new Vertex3d(1f, 0, z) });
		start();
	}

	public static void main(String[] args) {
		new Example();
	}
	float i = 0;
	public void start() {
		while (true) {
			i -= 0.001f;
			if (Display.isCloseRequested()) {
				Display.destroy();
				break;
			}
			Display.update();
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		    binder.translate(new Vertex3d(i/10f,i/10f,i));
			binder.render();

		}
	}
}
