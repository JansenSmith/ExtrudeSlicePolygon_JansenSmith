import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cube
import eu.mihosoft.vrl.v3d.Extrude
import eu.mihosoft.vrl.v3d.Polygon
import eu.mihosoft.vrl.v3d.Slice
import eu.mihosoft.vrl.v3d.Transform

def base = new Cube(20).toCSG()
			.difference(new Cube(5,10,20).toCSG())
			.difference(new Cube(10,5,20).toCSG())
			.rotz(5)
			.toZMin()
List<Polygon> polys = Slice.slice(base,new Transform(),0)
Polygon innerCross = polys.get(1)
println Extrude.isCCW(innerCross)
// Create a simple Z extrusion of a polygon
CSG crossExtrusion = Extrude.polygons(innerCross, 2.5)
//Extrude from polygon to polygon (with the same number of points!)
Polygon secondCross = innerCross
					.movez(10)// You can move the second polygon around to get complex extrusions
					.rotz(15)
					.roty(45)
//Move the complex extrusion off so it can be seen
CSG crossExtrusion2 = Extrude.polygons(innerCross, secondCross)
					.movex(50)

return [base.movez(20),polys,crossExtrusion,crossExtrusion2]