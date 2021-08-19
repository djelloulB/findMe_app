package fr.hb.retrouvezmoi.models;




import androidx.annotation.StringRes;

import fr.hb.retrouvezmoi.R;


public enum CivilityEnum {
    MR(R.string.civility_mr),
    MRS(R.string.civility_mrs);


    private int resourceId;

    CivilityEnum(@StringRes int stringRes) {
        this.resourceId = stringRes;
    }

    public int getResourceId() {
        return resourceId;
    }
}
