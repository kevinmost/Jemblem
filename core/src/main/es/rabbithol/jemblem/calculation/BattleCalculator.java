package es.rabbithol.jemblem.calculation;

import com.badlogic.ashley.core.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import es.rabbithol.jemblem.model.skill.CalculationSkill;

public class BattleCalculator {

  private final BattleCalculatorInfo attacker;
  private final BattleCalculatorInfo defender;

  public BattleCalculator(Entity attacker, Entity defender) {
    this.attacker = new BattleCalculatorInfo(attacker);
    this.defender = new BattleCalculatorInfo(defender);
    calculate();
  }

  public BattleCalculatorResult getAttackerResult() {
    return attacker.result;
  }

  public BattleCalculatorResult getDefenderResult() {
    return defender.result;
  }

  private void calculate() {
    // Using FE7 formulas from: http://fireemblem.wikia.com/wiki/Battle_Formulas
    final Map<BattleCalculationStepLabel, List<BattleCalculationStep>> attackerSpecialSteps =
        getSpecialStepsFor(attacker);
    final Map<BattleCalculationStepLabel, List<BattleCalculationStep>> defenderSpecialSteps =
        getSpecialStepsFor(defender);
    Stream.of(BattleCalculationStepLabel.values())
        .forEach(step -> {
          final List<BattleCalculationStep> attackerAllSteps = new ArrayList<>();
          attackerAllSteps.addAll(attackerSpecialSteps.get(step));
          attackerAllSteps.add(step.getDefaultImplementation());
          for (BattleCalculationStep attackerStep : attackerAllSteps) {
            if (!attackerStep.calculate(attacker, defender)) {
              break;
            }
          }
          final List<BattleCalculationStep> defenderAllSteps = new ArrayList<>();
          defenderAllSteps.addAll(defenderSpecialSteps.get(step));
          defenderAllSteps.add(step.getDefaultImplementation());
          for (BattleCalculationStep defenderStep : defenderAllSteps) {
            if (!defenderStep.calculate(defender, attacker)) {
              break;
            }
          }
        });
  }

  private Map<BattleCalculationStepLabel, List<BattleCalculationStep>> getSpecialStepsFor(
      BattleCalculatorInfo character) {
    return character.skills.stream()
        .map(CalculationSkill::modification)
        .map(inputList -> inputList.entrySet().stream().collect(Collectors.toMap(
            Map.Entry::getKey,
            entry -> Collections.singletonList(entry.getValue()))))
        .map(Map::entrySet)
        .flatMap(Collection::stream)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
            (left, right) -> Stream
                .concat(left.stream(), right.stream())
                .collect(Collectors.toList())
        ));
  }
}
