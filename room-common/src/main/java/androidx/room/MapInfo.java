/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.room;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Declares which column(s) are used to build a map or multimap return value in a {@link Dao}
 * query method.
 * <p>
 * This annotation is required when the key or value of the Map is a single column of one of the
 * built in types (primitives, boxed primitives, enum, String, byte[], ByteBuffer) or a type with a
 * converter (e.g. Date, UUID, etc).
 * <p>
 * The use of this annotation provides clarity on which column should be used in retrieving
 * information required by the return type.
 * <p>
 * Example:
 * <pre>
 *   {@literal @}MapInfo(keyColumn = "artistName", valueColumn = "songName")
 *   {@literal @}Query("SELECT * FROM Artist JOIN Song ON Artist.artistName = Song.artist")
 *    Map&lt;String, List&lt;String&gt;&gt; getArtistNameToSongNames();
 *
 *   {@literal @}MapInfo(valueColumn = "songCount")
 *   {@literal @}Query("SELECT *, COUNT(mSongId) as songCount FROM Artist JOIN Song ON
 *   Artist.artistName = Song.artist GROUP BY artistName")
 *    Map&lt;Artist, Integer&gt; getArtistAndSongCounts();
 * </pre>
 * <p>
 * To use the @MapInfo annotation, you must provide either the key column name, value column
 * name, or both, based on the {@link Dao}'s method return type. Column(s) specified in the
 * provided @MapInfo annotation must be present in the query result.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface MapInfo {
    /**
     * The name of the column to be used for the map's keys.
     *
     * @return The key column name.
     */
    String keyColumn() default "";

    /**
     * The name of the column to be used for the map's values.
     *
     * @return The value column name.
     */
    String valueColumn() default "";
}
