package com.inegru.android.atelieruldigital.helloworld.week6.fragments;

import android.os.Bundle;
import android.util.Log;

import com.inegru.android.atelieruldigital.helloworld.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * .
 */
public class DynamicFragmentActivity extends AppCompatActivity {

    /**
     * Custom TAG for identifying the first fragment.
     */
    private static final String FRAGMENT_TAG_FIRST = "fragment_tag_first";
    /**
     * Custom TAG for identifying the second fragment.
     */
    private static final String FRAGMENT_TAG_SECOND = "fragment_tag_second";

    /**
     * Custom TAG for handling logs.
     */
    private static final String LOG_TAG = "DynamicFragmentActivity";

    /**
     * Add a new fragment to the back stack of fragments for the current activity.
     *
     * @param fragmentManager The {@link FragmentManager} used to create fragment transactions
     * @param fragment        The fragment that will be added. Cannot be {@code null}
     * @param fragmentTag     Custom tag for identifying the fragment later on. Can {@code null} to
     *                        skip identification.
     */
    @SuppressWarnings("SameParameterValue")
    private static void addNewFragment(@NonNull FragmentManager fragmentManager,
                                       @NonNull Fragment fragment,
                                       @Nullable String fragmentTag) {
        // Begin the fragment transaction using the fragment manager
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Add the new fragment to the transaction
        transaction.add(R.id.fragment_holder, fragment, fragmentTag);

        // Commit the transaction (required to actually display the fragment)
        transaction.commit();
    }

    /**
     * Replace any existing fragment with the new fragment.
     *
     * @param fragmentManager The {@link FragmentManager} used to create fragment transactions
     * @param fragment        The fragment that will be added. Cannot be {@code null}
     * @param fragmentTag     Custom tag for identifying the fragment later on. Can {@code null} to
     *                        skip identification.
     */
    private static void replaceFragment(@NonNull FragmentManager fragmentManager,
                                        @NonNull Fragment fragment,
                                        @Nullable String fragmentTag) {
        // Begin the fragment transaction using the fragment manager
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Replace existing fragment(if any) with the new fragment through the transaction
        transaction.replace(R.id.fragment_holder, fragment, fragmentTag);

        // Commit the transaction (required to actually display the fragment)
        transaction.commit();
    }

    /**
     * Add a new {@link Fragment} to the activity using the {@link FragmentManager} and it's
     * utility {@link FragmentTransaction}.
     *
     * This utility method will alternate between {@link CustomFragment1} and
     * {@link CustomFragment2} fragment
     * for the add operation.
     *
     * @param manager The {@link FragmentManager} to access the current list of fragments.
     */
    private static void addNewFragment(FragmentManager manager) {
        // Obtain the reverse fragment with its tag
        Pair<Fragment, String> reverseFragmentPair = getReverseFragment(manager);

        if (reverseFragmentPair.first != null) {
            addNewFragment(manager, reverseFragmentPair.first, reverseFragmentPair.second);
        } else {
            Log.e(LOG_TAG, "addNewFragment(): Invalid reverse fragment!");
        }
    }

    /**
     * Change between {@link CustomFragment1} and {@link CustomFragment2} fragments.
     */
    private static void replaceFragment(@NonNull FragmentManager manager) {
        // Obtain the reverse fragment with its tag
        Pair<Fragment, String> reverseFragmentPair = getReverseFragment(manager);

        if (reverseFragmentPair.first != null) {
            replaceFragment(manager, reverseFragmentPair.first, reverseFragmentPair.second);
        } else {
            Log.e(LOG_TAG, "changeFragment(): Invalid reverse fragment!");
        }
    }

    /**
     * Get the reverse fragment:
     * - If current fragment is first than the second fragment will be returned.
     * - If current fragment is second than the first fragment will be returned.
     *
     * @param manager The {@link FragmentManager} to access the current list of fragments.
     *
     * @return {@link Pair} containing the reversed fragment and it's tag.
     */
    @NonNull
    private static Pair<Fragment, String> getReverseFragment(@NonNull FragmentManager manager) {
        List<Fragment> fragments = manager.getFragments();

        Fragment newFragment;
        String newTag;

        // Get the tag of the latest fragment add (if any)
        String tag = fragments.get(fragments.size() - 1).getTag();
        if (tag != null) {
            switch (tag) {
                case FRAGMENT_TAG_FIRST:
                default:
                    // Should add the second
                    newFragment = new CustomFragment2();
                    newTag = FRAGMENT_TAG_SECOND;
                    break;
                case FRAGMENT_TAG_SECOND:
                    // Should add the first
                    newFragment = new CustomFragment1();
                    newTag = FRAGMENT_TAG_FIRST;
                    break;
            }
        } else {
            // Invalid tag - force to add first
            newFragment = new CustomFragment1();
            newTag = FRAGMENT_TAG_FIRST;
        }

        return new Pair<>(newFragment, newTag);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for the activity (should include the holder for the fragments)
        setContentView(R.layout.activity_dynamic_fragment);

        // The manager through which we can access the stack of fragments available
        final FragmentManager manager = getSupportFragmentManager();

        // Add the initial fragment to the activity
        addNewFragment(manager, new CustomFragment1(), FRAGMENT_TAG_FIRST);

        findViewById(R.id.change_fragment)
            .setOnClickListener(v -> replaceFragment(manager));
        findViewById(R.id.add_new_fragment)
            .setOnClickListener(v -> addNewFragment(manager));
    }

    @Override
    public void onBackPressed() {
        if (popLatestFragment()) {
            super.onBackPressed();
        }
    }

    /**
     * Pop the latest fragment from the back stack.
     *
     * @return {@code true} if we should finish the activity, {@code false} if we should just
     * remove the latest fragment.
     */
    private boolean popLatestFragment() {
        // The manager through which we can access the stack of fragments available
        FragmentManager fm = getSupportFragmentManager();

        // The list of available fragments
        List<Fragment> fragments = fm.getFragments();
        if (fragments.size() == 1) {
            // Only 1 fragment available - we can directly close the activity
            return true;
        } else {
            /* We have more than 1 fragments in the stack -> remove latest */
            //  Open the transaction for the remove action
            FragmentTransaction tr = fm.beginTransaction();
            // Remove the latest fragment
            tr.remove(fragments.get(fragments.size() - 1));
            tr.commit();
            return false;
        }
    }
}
