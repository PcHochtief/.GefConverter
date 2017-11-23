/**
 *
 * Copyright 2012-2017 TNO Geologische Dienst Nederland
 *
 * Licensed under the EUPL, Version 1.2 or - as soon they will be approved by
 * the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/software/page/eupl
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 *
 * This work was sponsored by the Dutch Rijksoverheid, Basisregistratie
 * Ondergrond (BRO) Programme (https://bro.pleio.nl/)
 */
package nl.bro.dto.gef.validation.impl;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.junit.Test;

import nl.bro.dto.gef.validation.support.GefValidation;
import nl.bro.dto.gef.validation.IsNotBefore01011930;

public class TestIsAfter01011930Validator {

    public class IsAfter01011930Bean {

        @IsNotBefore01011930
        private String date;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

    }

    @Test
    public void testIsAfterValid() {
        IsAfter01011930Bean bean = new IsAfter01011930Bean();

        bean.setDate( "1930, 01, 01" );

        Validator validator = GefValidation.buildFactory().getValidator();
        Set<ConstraintViolation<IsAfter01011930Bean>> cvs = validator.validate( bean, Default.class );

        assertThat( cvs ).isEmpty();
    }

    @Test
    public void testIsBeforeInValid() {
        IsAfter01011930Bean bean = new IsAfter01011930Bean();

        bean.setDate( "1929, 12, 31" );

        Validator validator = GefValidation.buildFactory().getValidator();
        Set<ConstraintViolation<IsAfter01011930Bean>> cvs = validator.validate( bean, Default.class );

        assertThat( cvs.size() ).isEqualTo( 1 );
    }

}
