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

import static nl.bro.dto.gef.validation.support.GefMessageInterpolator.createSubstitutes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import nl.bro.cpt.gef.dto.GefCptFile;
import nl.bro.cpt.gef.transform.MappingConstants;
import nl.bro.dto.gef.validation.WaterDepthNotNullWhenLVRPWaterbodem;

public class WaterDepthNotNullWhenLVRPWaterbodemValidator implements ConstraintValidator<WaterDepthNotNullWhenLVRPWaterbodem, GefCptFile> {

    private WaterDepthNotNullWhenLVRPWaterbodem annotation;

    @Override
    public void initialize( WaterDepthNotNullWhenLVRPWaterbodem annotation ) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(GefCptFile cpt, ConstraintValidatorContext context) {

        boolean valid = true;
        String lrvp = null;
        if ( cpt.getMeasurementText9() != null ) {
            String lvrp = cpt.getMeasurementText9().toLowerCase();
            if ( MappingConstants.WATERBODEM_VALUES.contains( lvrp ) ) {
                valid = cpt.getMeasurementVar15() != null;
            }
        }

        if ( !valid ) {
            // just to add attribute / entity
            createSubstitutes( annotation, cpt ).addValue( "${vlrpValue}", lrvp );

        }
        return valid;
    }

}
