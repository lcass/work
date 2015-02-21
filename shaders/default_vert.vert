attribute vec3 vertex;
attribute vec2 texturecoordinate;
uniform vec3 rotation;
uniform vec3 translation;
uniform vec3 rotation_centre;
void main(){
	vec4 mvv = gl_ModelViewMatrix * vec4(vertex + translation, 1.0);
	gl_Position = gl_ProjectionMatrix *  mvv;
}