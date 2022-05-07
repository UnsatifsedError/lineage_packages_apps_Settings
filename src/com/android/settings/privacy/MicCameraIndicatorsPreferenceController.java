/*
 * Copyright (C) 2020 The Android Open Source Project
 * Copyright (C) 2022 Bianca Project
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

package com.android.settings.privacy;

import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.DeviceConfig;

import com.android.settings.R;
import com.android.settings.Utils;
import com.android.settings.core.TogglePreferenceController;

/** Controller for Camera and Mic indicators toggle. */
public class MicCameraIndicatorsPreferenceController extends TogglePreferenceController {

    public MicCameraIndicatorsPreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
    }

    @Override
    public boolean isChecked() {
        return DeviceConfig.getBoolean(DeviceConfig.NAMESPACE_PRIVACY,
                Utils.PROPERTY_MIC_CAMERA_ENABLED, false);
    }

    @Override
    public boolean setChecked(boolean isChecked) {
        DeviceConfig.setProperty(DeviceConfig.NAMESPACE_PRIVACY,
                Utils.PROPERTY_MIC_CAMERA_ENABLED, Boolean.toString(isChecked), true);
        return true;
    }

    @Override
    public int getAvailabilityStatus() {
        /** final boolean isEnabled = DeviceConfig.getBoolean(DeviceConfig.NAMESPACE_PRIVACY,
                Utils.PROPERTY_MIC_CAMERA_SETTINGS_ENABLED, false);
        if (!isEnabled) {
            return UNSUPPORTED_ON_DEVICE;
        }*/
        // Camera indicators feature is only available on devices that support location.
        return mContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)
                ? AVAILABLE : UNSUPPORTED_ON_DEVICE;
    }

    @Override
    public int getSliceHighlightMenuRes() {
        return R.string.menu_key_privacy ;
    }
}
