package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

import java.util.ArrayList;
import java.util.List;

import es.rabbithol.jemblem.model.skill.Skill;

public class SkillsComponent implements Component {
  public final List<Skill> skills = new ArrayList<>();
}
