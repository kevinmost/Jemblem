package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

import org.jetbrains.annotations.NotNull;

import es.rabbithol.jemblem.model.fe_class.FEClass;

public class FEClassComponent implements Component {
  @NotNull
  public FEClass feClass;

  public FEClassComponent(@NotNull FEClass feClass) {
    this.feClass = feClass;
  }
}
