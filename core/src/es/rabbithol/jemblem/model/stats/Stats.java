package es.rabbithol.jemblem.model.stats;

public interface Stats {

  static Stats get() {
    return new StatsAdapter();
  }

  int hp();

  int strength();

  int skill();

  int speed();

  int luck();

  int defense();

  int resistance();

  int constitution();

  int aid();

  int move();

  Stats hp(int hp);

  Stats strength(int strength);

  Stats skill(int skill);

  Stats speed(int speed);

  Stats luck(int luck);

  Stats defense(int defense);

  Stats resistance(int resistance);

  Stats constitution(int constitution);

  Stats aid(int aid);

  Stats move(int move);


  Stats NON_PROMOTED_MAX_STATS = Stats.get()
      .hp(60)
      .strength(20)
      .skill(20)
      .speed(20)
      .luck(30)
      .defense(20)
      .resistance(20)
      .constitution(20)
      .move(15);
}
