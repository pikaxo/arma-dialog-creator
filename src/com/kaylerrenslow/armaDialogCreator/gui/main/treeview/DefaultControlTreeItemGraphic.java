package com.kaylerrenslow.armaDialogCreator.gui.main.treeview;

import com.kaylerrenslow.armaDialogCreator.control.sv.AColor;
import com.kaylerrenslow.armaDialogCreator.gui.fxcontrol.BorderedImageView;
import com.kaylerrenslow.armaDialogCreator.util.ValueListener;
import com.kaylerrenslow.armaDialogCreator.util.ValueObserver;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

/**
 Graphic for tree items that represent a control

 @author Kayler
 @since 06/08/2016. */
public class DefaultControlTreeItemGraphic extends HBox {
	private static final Insets margin = new Insets(0, 5, 0, 0);
	private static final String BORDER_STYLE = "-fx-background-color:#b3b3b3,white;-fx-background-insets:0,20;-fx-padding:1px;";
	private final RadioButton rbSelected = new RadioButton();
	private final Canvas box = new Canvas(16, 16);
	private ControlTreeItemEntry entry;

	public DefaultControlTreeItemGraphic() {
		super(5);
		rbSelected.setSelected(true);
	}

	public void init(@NotNull ControlTreeItemEntry entry) {
		this.entry = entry;
		rbSelected.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				entry.setVisible(newValue);
			}
		});
		entry.getMyArmaControl().getRenderer().getEnabledObserver().addListener(new ValueListener<Boolean>() {
			@Override
			public void valueUpdated(@NotNull ValueObserver<Boolean> observer, Boolean oldValue, Boolean enabled) {
				updateVisibilityRadioButton(!entry.getMyArmaControl().getRenderer().isGhost());
			}
		});
		entry.getMyArmaControl().getRenderer().getBackgroundColorObserver().addListener(new ValueListener<AColor>() {
			@Override
			public void valueUpdated(@NotNull ValueObserver<AColor> observer, AColor oldValue, AColor newValue) {
				if (newValue == null) {
					fillBox(Color.TRANSPARENT);
				} else {
					fillBox(newValue.toJavaFXColor());
				}
			}
		});

		final BorderedImageView imageView = new BorderedImageView(entry.getMyArmaControl().getControlType().getIcon());
		Tooltip.install(imageView, new Tooltip(entry.getMyArmaControl().getControlType().getDisplayName()));

		fillBox(entry.getPrimaryColor());
		StackPane boxBorder = new StackPane(box);
		boxBorder.setStyle(BORDER_STYLE);
		HBox.setMargin(boxBorder, margin);
		getChildren().addAll(imageView, boxBorder, rbSelected);
	}

	private void fillBox(Color color) {
		GraphicsContext gc = box.getGraphicsContext2D();
		gc.save();
		gc.setFill(color);
		gc.fillRect(0, 0, box.getWidth(), box.getHeight());
		gc.restore();

		//generate hex string and bind it to tooltip
		final double f = 255.0;
		int r = (int) (color.getRed() * f);
		int g = (int) (color.getGreen() * f);
		int b = (int) (color.getBlue() * f);
		int a = (int) (color.getOpacity() * f);

		Tooltip.install(box, new Tooltip(String.format("red:%d, green:%d, blue:%d, alpha:%d", r, g, b, a)));
	}

	public void updateVisibilityRadioButton(boolean visible) {
		rbSelected.setSelected(visible);
	}

	public void setGraphicIsEnabled(boolean enabled) {
		this.getChildren().remove(getChildren().size() - 1);
		String lessVisibleText = "less-visible-tree-cell";
		if (enabled) {
			getChildren().add(rbSelected);
			entry.getStyleClass().remove(lessVisibleText);
		} else {
			CheckBox checkBox = new CheckBox();
			checkBox.setIndeterminate(true);
			checkBox.setDisable(true);
			getChildren().add(checkBox);
			entry.getStyleClass().add(lessVisibleText);
		}
	}
}
