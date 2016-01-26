package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

import org.jetbrains.annotations.NotNull;

import es.rabbithol.jemblem.model.fe_class.FEClass;
import es.rabbithol.jemblem.model.fe_class.FEClasses;

public class FEClassComponent implements Component {
  public static final FEClassComponent NULL_CLASS = new FEClassComponent(FEClasses.NULL);

  @NotNull
  public FEClass feClass;

  public FEClassComponent(@NotNull FEClass feClass) {
    this.feClass = feClass;
  }
}
