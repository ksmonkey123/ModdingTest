package ch.modjam.generic.blocks.customRenderer;

import net.minecraft.client.renderer.Tessellator;
import ch.modjam.generic.blocks.Coord;
import ch.modjam.generic.blocks.EFace;

public class Geometry2 extends Geometry {

	/**
	 * the amount of tiles that are beside each other in the texture (only width)
	 */
	private final static int		tileSize	= 4;
	/**
	 * float value that is used per tile of the texture
	 */
	private final static double		uvPerTile	= 1. / tileSize;

	/**
	 * 1st index: 0=x,1=y,2=z<br>
	 * 2nd index: sideIndex<br>
	 * defines the mapping from the side width to dx, dy and dz
	 */
	private static final int[][]	widthToXYZ	= { //
												{ 0, 0, 0, 1, 0, 1 }, //
												{ 0, 0, 0, 0, 0, 0 }, //
												{ 1, 1, 1, 0, 1, 0 } };

	private static final int[][]	heightToXYZ	= { //
												{ 1, 1, 0, 0, 0, 0 }, //
												{ 0, 0, 1, 1, 1, 1 }, //
												{ 0, 0, 0, 0, 0, 0 } };

	public Geometry2(String texture) {
		super(texture);
	}

	public void quad(double x, double y, double z, double width, double height, EFace visibleFrom) {
		quad(x, y, z, width, height, visibleFrom, visibleFrom.getTileIndex(), 0);
	}

	public void quad(double x, double y, double z, double width, double height, EFace visibleFrom,
			int useTextureTileIndex) {
		quad(x, y, z, width, height, visibleFrom, useTextureTileIndex, 0);
	}

	public void quad(double x, double y, double z, double width, double height, EFace visibleFrom,
			int useTextureTileIndex, int texRotation) {

		double[] delta = sizeToDeltaCoords(visibleFrom, width, height);

		double xp = x + delta[0];
		double yp = y + delta[1];
		double zp = z + delta[2];
		double[][] coord = { { -xp, x }, { -yp, y }, { -zp, z } };

		int tx = useTextureTileIndex % 4;
		int ty = useTextureTileIndex / 4;

		double us = 0, vs = 0, dU, dV;

		boolean swaped = (texRotation % 2) == 1;

		int[] uc, vc; // {value (+delta[n]/2 or -delta[n]/2), coordinate
						// (0=x,1=y,2=z)}

		switch (visibleFrom) {
			case TOP: {
				uc = arr(0, Coord.X.index);
				vc = arr(0, Coord.Z.index);
				swaped = !swaped;
				break;
			}
			case FRONT: {
				uc = arr(0, Coord.X.index);
				vc = arr(0, Coord.Y.index);
				break;
			}
			case LEFT: {
				uc = arr(0, Coord.Z.index);
				vc = arr(0, Coord.Y.index);
				break;
			}
			case RIGHT: {
				uc = arr(1, Coord.Z.index);
				vc = arr(0, Coord.Y.index);
				break;
			}
			case BACK: {
				uc = arr(1, Coord.X.index);
				vc = arr(0, Coord.Y.index);
				break;
			}
			case BOTTOM: {
				uc = arr(0, Coord.X.index);
				vc = arr(1, Coord.Z.index);
				swaped = !swaped;
				break;
			}
			default: {
				throw new RuntimeException("Unhandled enum type: " + visibleFrom);
			}
		}

		int rot = texRotation;
		while (rot > 0) {
			rot--;
			int[] temp = uc;
			uc = arr(vc[0], vc[1]);
			vc = arr(1 - temp[0], temp[1]);
		}

		us = tx * uvPerTile + (0.5 + coord[uc[1]][uc[0]]) * uvPerTile;
		vs = ty * uvPerTile + (0.5 + coord[vc[1]][vc[0]]) * uvPerTile;

		if (!swaped) {
			dU = width * uvPerTile;
			dV = height * uvPerTile;
		} else {
			dU = height * uvPerTile;
			dV = width * uvPerTile;
		}

		double up = us + dU;
		double vp = vs + dV;

		double[] u = { us, us, up, up };
		double[] v = { vs, vp, vp, vs };
		double[] cx = null;
		double[] cy = null;
		double[] cz = null;

		switch (visibleFrom) {
			case TOP: {
				cx = new double[] { xp, xp, x, x };
				cy = new double[] { y, y, y, y };
				cz = new double[] { zp, z, z, zp };
				break;
			}
			case FRONT: {
				cx = new double[] { xp, xp, x, x };
				cy = new double[] { yp, y, y, yp };
				cz = new double[] { z, z, z, z };
				break;
			}
			case LEFT: {
				cx = new double[] { x, x, x, x };
				cy = new double[] { yp, y, y, yp };
				cz = new double[] { zp, zp, z, z };
				break;
			}
			case RIGHT: {
				cx = new double[] { x, x, x, x };
				cy = new double[] { yp, y, y, yp };
				cz = new double[] { z, z, zp, zp };
				break;
			}
			case BACK: {
				cx = new double[] { x, x, xp, xp };
				cy = new double[] { yp, y, y, yp };
				cz = new double[] { z, z, z, z };
				break;
			}
			case BOTTOM: // fixes potential nullpointer warning
			default: {
				cx = new double[] { xp, xp, x, x };
				cy = new double[] { y, y, y, y };
				cz = new double[] { z, zp, zp, z };
				break;
			}
		}

		for (int i = 0; i < 4; i++)
			Tessellator.instance.addVertexWithUV(cx[i], cy[i], cz[i], u[(i + texRotation) % 4],
				v[(i + texRotation) % 4]);
	}

	private static double[] sizeToDeltaCoords(EFace visibleFrom, double width, double height) {
		int sideI = visibleFrom.getIndex();
		double[] delta = new double[3];
		for (int i = 0; i < 3; i++)
			delta[i] = width * widthToXYZ[i][sideI] + height * heightToXYZ[i][sideI];
		return delta;
	}

	private static int[] arr(int... values) {
		return values;
	}

}
