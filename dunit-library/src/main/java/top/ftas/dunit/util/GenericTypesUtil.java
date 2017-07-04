package top.ftas.dunit.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

import top.ftas.dunit.group.DUnitGroupInterface;

/**
 * Created by tik on 17/6/28.
 * Gson对泛型的支持
 */

public class GenericTypesUtil {

	public static ParameterizedType type(final Class raw, final Type... genericArgs) {
		return new ParameterizedType() {
			public Type getRawType() {
				return raw;
			}

			public Type[] getActualTypeArguments() {
				return genericArgs;
			}

			public Type getOwnerType() {
				return null;
			}
		};
	}

	public static ParameterizedType getWildcardType(final Class raw,final Type... upperTypes){
		return new ParameterizedType() {
			private WildcardType wildcardType = new WildcardType() {
				@Override
				public Type[] getUpperBounds() {
					return upperTypes;
				}

				@Override
				public Type[] getLowerBounds() {
					return new Type[0];
				}
			};

			@Override
			public Type[] getActualTypeArguments() {
				return new Type[]{wildcardType};
			}

			@Override
			public Type getRawType() {
				return raw;
			}

			@Override
			public Type getOwnerType() {
				return null;
			}
		};
	}
}
