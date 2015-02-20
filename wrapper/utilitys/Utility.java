package wrapper.utilitys;

import wrapper.graphics.texture.spritecomponent;

public class Utility {
	//all methods in this class are static for ease of use.
	public Vertex3d ray_cast(Vertex3d source,Vertex3d target,Vertex3d[] checks){
		
		
		
		
		return null;
	}
	public Vertex2d to_texture_coordinates(float x, float y ,float increment){
		return new Vertex2d(x * (1/increment), y * (1/increment));
	}
	public Vertex2d[] square(Vertex2d source , spritecomponent s){
		return null;
	}
	public Vertex3d[] cube(Vertex3d source , spritecomponent[] s){
		return null;
	}
	public ProgressiveBuffer[] square_progressive(Vertex2d source , spritecomponent s){
		return null;
	}
	public ProgressiveBuffer[] cube_progressive(){
		return null;
	}
	public ProgressiveBuffer[] to_progressive(Vertex2d data){
		null;
	}
	public ProgressiveBuffer[] to_progressive(Vertex3d data){
		return null;
	}
}
