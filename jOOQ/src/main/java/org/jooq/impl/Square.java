/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Other licenses:
 * -----------------------------------------------------------------------------
 * Commercial licenses for this work are available. These replace the above
 * ASL 2.0 and offer limited warranties, support, maintenance, and commercial
 * database integrations.
 *
 * For more information, please visit: http://www.jooq.org/licenses
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package org.jooq.impl;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.Internal.*;
import static org.jooq.impl.Keywords.*;
import static org.jooq.impl.Names.*;
import static org.jooq.impl.SQLDataType.*;
import static org.jooq.impl.Tools.*;
import static org.jooq.impl.Tools.BooleanDataKey.*;
import static org.jooq.SQLDialect.*;

import org.jooq.*;
import org.jooq.conf.*;
import org.jooq.impl.*;
import org.jooq.tools.*;

import java.util.*;


/**
 * The <code>SQUARE</code> statement.
 */
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
final class Square<T extends Number>
extends
    AbstractField<T>
{

    private static final long serialVersionUID = 1L;

    private final Field<T> value;

    Square(
        Field<T> value
    ) {
        super(
            N_SQUARE,
            allNotNull((DataType) dataType(INTEGER, value, false), value)
        );

        this.value = nullSafeNotNull(value, INTEGER);
    }

    // -------------------------------------------------------------------------
    // XXX: QueryPart API
    // -------------------------------------------------------------------------



    private static final Set<SQLDialect> NO_SUPPORT_SQUARE = SQLDialect.supportedBy(CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE);

    @Override
    public final void accept(Context<?> ctx) {
        if (NO_SUPPORT_SQUARE.contains(ctx.dialect()))
            ctx.visit(value.times(value));
        else
            ctx.visit(N_SQUARE).sql('(').visit(value).sql(')');
    }



    // -------------------------------------------------------------------------
    // The Object API
    // -------------------------------------------------------------------------

    @Override
    public boolean equals(Object that) {
        if (that instanceof Square) {
            return
                StringUtils.equals(value, ((Square) that).value)
            ;
        }
        else
            return super.equals(that);
    }
}
