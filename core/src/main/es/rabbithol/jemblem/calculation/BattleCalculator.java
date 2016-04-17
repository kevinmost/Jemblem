package es.rabbithol.jemblem.calculation;

import com.badlogic.ashley.core.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
    Stream.of(BattleCalculationStepLabel.values())
        .forEach(step -> {
          final BattleCalculationStep defaultImpl = step.getDefaultImplementation();
          attackerSpecialSteps.get(step)
              .stream()
              .forEach(specialCalculation -> specialCalculation.calculate(attacker, defender));
          defaultImpl.calculate(attacker, defender);
          defaultImpl.compose(defenderSkills).calculate(defender, attacker);
        });
  }

  private Map<BattleCalculationStepLabel, List<BattleCalculationStep>> getSpecialStepsFor(
      BattleCalculatorInfo character) {
    return character.skills.stream()
        .map(CalculationSkill::modification)
        .map(new MapValuesToSingletonListFunction())
        .map(Map::entrySet)
        .flatMap(Collection::stream)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (left, right) -> {
          final ArrayList<BattleCalculationStep> merged = new ArrayList<>();
          merged.addAll(left);
          merged.addAll(right);
          return merged;
        }));
  }

  // TODO: This is a monument to my sins
  private static class MapValuesToSingletonListFunction implements
      Function<Map<BattleCalculationStepLabel, BattleCalculationStep>,
          Map<BattleCalculationStepLabel, List<BattleCalculationStep>>> {
    @Override
    public Map<BattleCalculationStepLabel, List<BattleCalculationStep>> apply(
        Map<BattleCalculationStepLabel, BattleCalculationStep> inputList) {

      final Map<BattleCalculationStepLabel, List<BattleCalculationStep>> result = new HashMap<>();

      for (Map.Entry<BattleCalculationStepLabel, BattleCalculationStep> step : inputList.entrySet()) {
        final List<BattleCalculationStep> singletonList = new ArrayList<>();
        singletonList.add(step.getValue());
        result.put(step.getKey(), singletonList);
      }
      return result;
    }
  }
}
