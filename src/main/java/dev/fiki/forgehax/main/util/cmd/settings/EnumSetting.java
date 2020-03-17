package dev.fiki.forgehax.main.util.cmd.settings;

import dev.fiki.forgehax.main.util.cmd.AbstractSetting;
import dev.fiki.forgehax.main.util.cmd.IParentCommand;
import dev.fiki.forgehax.main.util.cmd.flag.EnumFlag;
import dev.fiki.forgehax.main.util.typeconverter.IConverter;
import dev.fiki.forgehax.main.util.typeconverter.type.EnumType;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

import java.util.Collection;
import java.util.List;

@Getter
public final class EnumSetting<E extends Enum<E>> extends AbstractSetting<E> {
  private final IConverter<E> converter;

  @Builder
  public EnumSetting(IParentCommand parent,
      String name, @Singular Collection<String> aliases, String description,
      @Singular Collection<EnumFlag> flags,
      @Singular List<ISettingValueChanged<Enum<E>>> changedListeners,
      @NonNull E defaultTo, E min, E max) {
    super(parent, name, aliases, description, flags, defaultTo, min, max);
    this.converter = new EnumType<>(defaultTo.getDeclaringClass());
    addListeners(ISettingValueChanged.class, changedListeners);
    onFullyConstructed();
  }
}
