package io.github.ytg1234.recipeconditions.impl.condition.base;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.ytg1234.recipeconditions.api.condition.base.RecipeCondition;
import io.github.ytg1234.recipeconditions.api.condition.base.RecipeConditionParameter;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.Internal
public final class RecipeConditionParameterImpl implements RecipeConditionParameter {
    @NotNull
    private final JsonElement value;

    public RecipeConditionParameterImpl(@NotNull JsonElement json) {
        this.value = json;
    }

    @NotNull
    @Override
    public JsonElement getValue() {
        return value;
    }

    @Override
    public String string() {
        return value.getAsString();
    }

    @Override
    public int integer() {
        return value.getAsInt();
    }

    @Override
    public double doublePrecision() {
        return value.getAsDouble();
    }

    @Override
    public float floatingPoint() {
        return value.getAsFloat();
    }

    @Override
    public JsonObject object() {
        return value.getAsJsonObject();
    }

    @Override
    public boolean bool() {
        return value.getAsBoolean();
    }

    @Override
    public JsonArray array() {
        return value.getAsJsonArray();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
