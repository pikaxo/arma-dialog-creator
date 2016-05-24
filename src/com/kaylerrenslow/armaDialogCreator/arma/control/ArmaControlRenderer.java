package com.kaylerrenslow.armaDialogCreator.arma.control;

import com.kaylerrenslow.armaDialogCreator.gui.canvas.api.ui.Component;
import com.kaylerrenslow.armaDialogCreator.gui.canvas.api.ui.Region;

/**
 @author Kayler
 Base class for JavaFX canvas rendering of arma controls
 Created on 05/20/2016.
 */
public class ArmaControlRenderer extends Component {
	private ArmaControl myControl;

	public ArmaControlRenderer() {
		super(0, 0, 0, 0);
	}

	final void setMyControl(ArmaControl myControl) {
		this.myControl = myControl;
	}

	@Override
	public void translate(int dx, int dy) {
		super.translate(dx, dy);
		myControl.calcPositionFromRenderer();
	}

	@Override
	public void scale(int dxl, int dxr, int dyt, int dyb) {
		super.scale(dxl, dxr, dyt, dyb);
		myControl.calcSizeFromRenderer();
	}

	@Override
	public void setPosition(Region r) {
		super.setPosition(r);
		myControl.calcPositionFromRenderer();
	}

	@Override
	public void setPosition(int x1, int y1, int x2, int y2) {
		super.setPosition(x1, y1, x2, y2);
		myControl.calcPositionFromRenderer();
		myControl.calcSizeFromRenderer();
	}

	@Override
	public void setPositionWH(int x1, int y1, int width, int height) {
		super.setPositionWH(x1, y1, width, height);
		myControl.calcPositionFromRenderer();
		myControl.calcSizeFromRenderer();
	}

	@Override
	public void setX1(int x1) {
		super.setX1(x1);
		myControl.calcPositionFromRenderer();
	}

	@Override
	public void setY1(int y1) {
		super.setY1(y1);
		myControl.calcPositionFromRenderer();
	}

	@Override
	public void setX2(int x2) {
		super.setX2(x2);
		myControl.calcSizeFromRenderer();
	}

	@Override
	public void setY2(int y2) {
		super.setY2(y2);
		myControl.calcSizeFromRenderer();
	}
}
